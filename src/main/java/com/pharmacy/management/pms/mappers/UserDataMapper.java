package com.pharmacy.management.pms.mappers;

import com.pharmacy.management.pms.dto.RegisterPharmacistDto;
import com.pharmacy.management.pms.dto.UserDto;
import com.pharmacy.management.pms.entities.User;

public class UserDataMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(),
                user.getPhoneNumber(), user.getStatus(),
                user.getRole(), user.getPassword());
    }

    public static User mapToUser(UserDto userDto) {
        return User.builder().id(userDto.getId())
                .firstname(userDto.getFirstName())
                .lastname(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .status(userDto.getStatus())
                .role(userDto.getRole())
                .password(userDto.getPassword())
                .build();
    }

    public static User mapToUser(RegisterPharmacistDto pharmacist) {
        return User.builder().id(pharmacist.getId())
                .firstname(pharmacist.getFirstName())
                .lastname(pharmacist.getLastName())
                .email(pharmacist.getEmail())
                .phoneNumber(pharmacist.getPhoneNumber())
                .status(pharmacist.getStatus())
                .role(pharmacist.getRole())
                .password(pharmacist.getPassword())
                .build();
    }
}
