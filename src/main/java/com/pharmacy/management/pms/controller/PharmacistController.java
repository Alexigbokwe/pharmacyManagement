package com.pharmacy.management.pms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.management.pms.dto.UpdatePharmacistDto;
import com.pharmacy.management.pms.dto.UserDto;
import com.pharmacy.management.pms.dto.UserStatusDto;
import com.pharmacy.management.pms.service.UserService;
import com.pharmacy.management.pms.utils.SuccessResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/pharmacist")
public class PharmacistController extends BaseController {
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/update_pharmacist_status/{id}")
    public ResponseEntity<SuccessResponse<String>> updatePharmacistStatus(@PathVariable("id") Integer id,
            @Valid @RequestBody UserStatusDto status) {
        this.userService.updatePharmacistStatus(id, status.getStatus());
        return ResponseEntity
                .ok(this.successResponse("Status successfully updated", null));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/update_pharmacist/{id}")
    public ResponseEntity<SuccessResponse<UserDto>> updatePharmacist(@PathVariable("id") Integer id,
            @RequestBody UpdatePharmacistDto pharmacist) {
        System.out.println(pharmacist.toString());
        UserDto updatedPharmacist = this.userService.updatePharmacist(id, pharmacist);
        return ResponseEntity
                .ok(this.successResponse("Pharmacist successfully updated", updatedPharmacist));
    }
}
