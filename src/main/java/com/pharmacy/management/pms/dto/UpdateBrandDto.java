package com.pharmacy.management.pms.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateBrandDto {
    @NotNull(message = "Brand id is required")
    private Integer id;

    @NotBlank(message = "Brand name is required")
    private String name;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
