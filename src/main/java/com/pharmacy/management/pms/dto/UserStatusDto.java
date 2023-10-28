package com.pharmacy.management.pms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserStatusDto {
    @NotNull(message = "Status is required")
    private String status;
}
