package com.pharmacy.management.pms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.management.pms.dto.LoginDto;
import com.pharmacy.management.pms.dto.RegisterPharmacistDto;

import com.pharmacy.management.pms.service.UserService;
import com.pharmacy.management.pms.utils.SuccessResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController extends BaseController {
    private UserService userService;

    @PostMapping("/register_pharmacist")
    public ResponseEntity<SuccessResponse<String>> registerPharmacist(
            @Valid @RequestBody RegisterPharmacistDto pharmacist) {
        this.userService.registerPharmacist(pharmacist);
        return ResponseEntity
                .ok(this.successResponse("Pharmacist successfully registered, awaiting approval from Admin", null));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<String>> login(@Valid @RequestBody LoginDto login) {
        String token = this.userService.login(login);
        return ResponseEntity
                .ok(this.successResponse("User successfully logged in", token));
    }
}
