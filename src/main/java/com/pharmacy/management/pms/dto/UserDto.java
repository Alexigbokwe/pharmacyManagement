package com.pharmacy.management.pms.dto;

import com.pharmacy.management.pms.configuration.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String status;
    private Role role;
    private String password;
}
