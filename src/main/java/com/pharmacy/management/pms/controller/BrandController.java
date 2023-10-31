package com.pharmacy.management.pms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.management.pms.dto.BrandDto;
import com.pharmacy.management.pms.dto.UpdateBrandDto;
import com.pharmacy.management.pms.service.BrandService;
import com.pharmacy.management.pms.utils.SuccessResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandController extends BaseController {
    private BrandService brandService;

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<BrandDto>>> getAllBrands() {
        List<BrandDto> brands = this.brandService.getAllBrands();
        return ResponseEntity.ok(this.successResponse("Brands successfully fetched", brands));
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessResponse<BrandDto>> getBrand(@PathVariable("id") Integer brandId) {
        BrandDto brand = this.brandService.getBrand(brandId);
        return ResponseEntity.ok(this.successResponse("Brand successfully fetched", brand));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<BrandDto>> saveBrand(@Valid @RequestBody BrandDto brand) {
        BrandDto createdBrand = this.brandService.createBrand(brand);
        return ResponseEntity.ok(this.successResponse("Brand successfully created", createdBrand));
    }

    @PatchMapping
    public ResponseEntity<SuccessResponse<BrandDto>> updateBrand(@Valid @RequestBody UpdateBrandDto brand) {
        BrandDto updatedBrand = this.brandService.updateBrand(brand);
        return ResponseEntity.ok(this.successResponse("Brand successfully updated", updatedBrand));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessResponse<String>> deleteBrand(@PathVariable("id") Integer brandId) {
        this.brandService.deleteBrand(brandId);
        return ResponseEntity.ok(this.successResponse("Brand successfully deleted", null));
    }
}
