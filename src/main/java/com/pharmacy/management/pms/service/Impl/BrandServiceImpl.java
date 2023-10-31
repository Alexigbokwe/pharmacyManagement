package com.pharmacy.management.pms.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pharmacy.management.pms.dto.BrandDto;
import com.pharmacy.management.pms.dto.UpdateBrandDto;
import com.pharmacy.management.pms.entities.Brand;
import com.pharmacy.management.pms.exception.ResourceAlreadyExistException;
import com.pharmacy.management.pms.exception.ResourceNotFoundException;
import com.pharmacy.management.pms.mappers.BrandDataMapper;
import com.pharmacy.management.pms.repository.BrandRepository;
import com.pharmacy.management.pms.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        if (this.doesBrandExistByName(brandDto.getName())) {
            throw new ResourceAlreadyExistException(
                    "Brand with the name " + brandDto.getName() + " already exists.");
        }
        Brand brand = BrandDataMapper.mapToBrand(brandDto);
        return BrandDataMapper.mapToBrandDto(this.brandRepository.save(brand));
    }

    private Boolean doesBrandExistByName(String brandName) {
        return this.brandRepository.findByName(brandName).isPresent();
    }

    private Boolean doesBrandExistById(Integer id) {
        return this.brandRepository.findById(id).isPresent();
    }

    @Override
    public BrandDto updateBrand(UpdateBrandDto reqBrand) {
        BrandDto brandDto = BrandDataMapper.mapToBrandDto(reqBrand);
        if (!this.doesBrandExistById(brandDto.getId())) {
            throw new ResourceNotFoundException("Brand not found");
        }
        Brand brand = BrandDataMapper.mapToBrand(brandDto);
        return BrandDataMapper.mapToBrandDto(this.brandRepository.save(brand));
    }

    @Override
    public List<BrandDto> getAllBrands() {
        List<Brand> brands = this.brandRepository.findAll();
        if (brands.isEmpty())
            throw new ResourceNotFoundException("No brand found in the system");
        return brands.stream().map(brand -> BrandDataMapper.mapToBrandDto(brand)).collect(Collectors.toList());
    }

    @Override
    public BrandDto getBrand(Integer brandId) {
        Brand brand = this.brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("No record found"));
        return BrandDataMapper.mapToBrandDto(brand);
    }

    @Override
    public void deleteBrand(Integer brandId) {
        this.brandRepository.deleteById(brandId);
    }

}
