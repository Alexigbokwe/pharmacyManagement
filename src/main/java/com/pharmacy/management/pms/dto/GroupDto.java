package com.pharmacy.management.pms.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private Integer id;

    @NotBlank(message = "Group name is required")
    private String name;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
