package com.pharmacy.management.pms.service;

import com.pharmacy.management.pms.dto.LoginDto;
import com.pharmacy.management.pms.dto.RegisterPharmacistDto;
import com.pharmacy.management.pms.dto.UpdatePharmacistDto;
import com.pharmacy.management.pms.dto.UserDto;

public interface UserService {
    public void registerPharmacist(RegisterPharmacistDto pharmacist);

    public String login(LoginDto login);

    public void updatePharmacistStatus(Integer id, String status);

    public UserDto updatePharmacist(Integer id, UpdatePharmacistDto userDto);
}
