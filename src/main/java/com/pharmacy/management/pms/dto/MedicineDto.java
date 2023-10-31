package com.pharmacy.management.pms.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {
    private Integer id;
    @NotBlank(message = "Medicine name is required")
    private String name;

    private String description;

    @NotBlank(message = "Medicine cost price is required")
    private String costPrice;

    @NotBlank(message = "Medicine selling price is required")
    private String sellingPrice;

    @NotBlank(message = "Medicine side effect is required")
    private String sideEffect;

    @NotBlank(message = "Medicine how to use is required")
    private String howToUse;

    @NotBlank(message = "Medicine quantity is required")
    private int quantityInStock;

    @NotBlank(message = "Medicine manufacture date is required")
    private Date manufacturedDate;

    @NotBlank(message = "Medicine expiration date is required")
    private Date expirationDate;

    @NotBlank(message = "Medicine group is required")
    private Integer medicineGroupId;

    @NotBlank(message = "Medicine brand is required")
    private Integer brandId;
}
