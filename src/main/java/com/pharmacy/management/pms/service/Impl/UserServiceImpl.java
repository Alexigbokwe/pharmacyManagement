package com.pharmacy.management.pms.service.Impl;

import static com.pharmacy.management.pms.configuration.Role.ADMIN;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pharmacy.management.pms.configuration.Role;
import com.pharmacy.management.pms.configuration.UserStatus;
import com.pharmacy.management.pms.dto.LoginDto;
import com.pharmacy.management.pms.dto.RegisterPharmacistDto;
import com.pharmacy.management.pms.dto.UpdatePharmacistDto;
import com.pharmacy.management.pms.dto.UserDto;
import com.pharmacy.management.pms.entities.Token;
import com.pharmacy.management.pms.entities.User;
import com.pharmacy.management.pms.exception.AuthorizationException;
import com.pharmacy.management.pms.exception.BadRequestException;
import com.pharmacy.management.pms.exception.ResourceAlreadyExistException;
import com.pharmacy.management.pms.exception.ResourceNotFoundException;
import com.pharmacy.management.pms.mappers.UserDataMapper;
import com.pharmacy.management.pms.repository.TokenRepository;
import com.pharmacy.management.pms.repository.UserRepository;
import com.pharmacy.management.pms.service.JwtService;
import com.pharmacy.management.pms.service.UserService;
import com.pharmacy.management.pms.utils.PasswordHashing;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private JwtService jwtService;

    @Override
    public void registerPharmacist(RegisterPharmacistDto pharmacist) {
        Boolean userExist = this.userRepository.findByEmail(pharmacist.getEmail().toLowerCase()).isPresent();
        if (userExist) {
            throw new ResourceAlreadyExistException("Account already exist, try to login or contact the admin.");
        }
        pharmacist.setRole(Role.PHARMACIST);
        pharmacist.setStatus(UserStatus.inactive);
        if (!pharmacist.getPassword().equals(pharmacist.getConfirmPassword())) {
            throw new BadRequestException("Password does not match");
        }
        String hashedPassword = PasswordHashing.hashPassword(pharmacist.getPassword());
        pharmacist.setPassword(hashedPassword);
        this.userRepository.save(UserDataMapper.mapToUser(pharmacist));
    }

    @Override
    public String login(LoginDto login) {
        Optional<User> user = this.userRepository.findByEmail(login.getEmail().toLowerCase());

        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User does not exist");
        }
        User dbUser = user.get();
        Boolean doesPasswordMatch = PasswordHashing.verifyPassword(login.getPassword(), dbUser.getPassword());
        if (!doesPasswordMatch) {
            throw new ResourceNotFoundException("Wrong Login details");
        }
        String userToken = this.jwtService.generateToken(dbUser);
        Token token = Token.builder().user(dbUser).token(userToken).build();
        this.tokenRepository.save(token);
        return userToken;

    }

    @Override
    public void updatePharmacistStatus(Integer id, String status) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User does not exist");
        }
        if (!user.getRole().equals(ADMIN)) {
            throw new AuthorizationException();
        }
        if (UserStatus.active.equalsIgnoreCase(status) || UserStatus.inactive.equalsIgnoreCase(status)) {
            user.setStatus(status);
            this.userRepository.save(user);
            return;
        }
        throw new BadRequestException("Invalid status provided");
    }

    @Override
    public UserDto updatePharmacist(Integer id, UpdatePharmacistDto userDto) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User does not exist");
        }

        if (user.getStatus().equals(UserStatus.inactive)) {
            throw new AuthorizationException();
        }

        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        User savedUser = this.userRepository.save(user);
        return UserDataMapper.mapToUserDto(savedUser);
    }

}
