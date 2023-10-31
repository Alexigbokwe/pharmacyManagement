package com.pharmacy.management.pms.mappers;

import com.pharmacy.management.pms.dto.BrandDto;
import com.pharmacy.management.pms.dto.UpdateBrandDto;
import com.pharmacy.management.pms.entities.Brand;

public class BrandDataMapper {
    public static BrandDto mapToBrandDto(Brand brand) {
        return new BrandDto(brand.getId(), brand.getName(),
                brand.getCreatedAt(),
                brand.getUpdatedAt());
    }

    public static BrandDto mapToBrandDto(UpdateBrandDto brand) {
        return new BrandDto(brand.getId(), brand.getName(),
                brand.getCreatedAt(),
                brand.getUpdatedAt());
    }

    public static Brand mapToBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setId(brandDto.getId());
        brand.setName(brandDto.getName());
        brand.setCreatedAt(brandDto.getCreatedAt());
        brand.setUpdatedAt(brandDto.getUpdatedAt());
        return brand;
    }
}
