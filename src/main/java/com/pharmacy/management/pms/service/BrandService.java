package com.pharmacy.management.pms.service;

import java.util.List;

import com.pharmacy.management.pms.dto.BrandDto;
import com.pharmacy.management.pms.dto.UpdateBrandDto;

public interface BrandService {
    BrandDto createBrand(BrandDto brandDto);

    BrandDto updateBrand(UpdateBrandDto brandDto);

    List<BrandDto> getAllBrands();

    BrandDto getBrand(Integer brandId);

    void deleteBrand(Integer brandId);
}
