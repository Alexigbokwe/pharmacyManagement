package com.pharmacy.management.pms.mappers;

import com.pharmacy.management.pms.dto.MedicineDto;
import com.pharmacy.management.pms.dto.UpdateMedicineDto;
import com.pharmacy.management.pms.entities.Brand;
import com.pharmacy.management.pms.entities.Medicine;
import com.pharmacy.management.pms.entities.MedicineGroup;
import com.pharmacy.management.pms.exception.ResourceNotFoundException;
import com.pharmacy.management.pms.repository.BrandRepository;
import com.pharmacy.management.pms.repository.GroupRepository;

public class MedicineDataMapper {
    public static MedicineDto mapToMedicineDto(Medicine medicine) {
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setId(medicine.getId());
        medicineDto.setName(medicine.getName());
        medicineDto.setDescription(medicine.getDescription());
        medicineDto.setCostPrice(medicine.getCostPrice());
        medicineDto.setSellingPrice(medicine.getSellingPrice());
        medicineDto.setSideEffect(medicine.getSideEffect());
        medicineDto.setHowToUse(medicine.getHowToUse());
        medicineDto.setSellingPrice(medicine.getSellingPrice());
        medicineDto.setManufacturedDate(medicine.getManufacturedDate());
        medicineDto.setExpirationDate(medicine.getExpirationDate());

        // Handle null category or brand
        if (medicine.getMedicine_group() != null) {
            medicineDto.setMedicineGroupId(medicine.getMedicine_group().getId());
        }
        if (medicine.getBrand() != null) {
            medicineDto.setBrandId(medicine.getBrand().getId());
        }

        return medicineDto;
    }

    public static Medicine mapToMedicine(UpdateMedicineDto updateMedicineDto, GroupRepository groupRepository,
            BrandRepository brandRepository) {
        Medicine medicine = new Medicine();
        medicine.setId(updateMedicineDto.getId());
        medicine.setName(updateMedicineDto.getName());
        medicine.setDescription(updateMedicineDto.getDescription());
        medicine.setCostPrice(updateMedicineDto.getCostPrice());
        medicine.setSellingPrice(updateMedicineDto.getSellingPrice());
        medicine.setSideEffect(updateMedicineDto.getSideEffect());
        medicine.setHowToUse(updateMedicineDto.getHowToUse());
        medicine.setSellingPrice(updateMedicineDto.getSellingPrice());
        medicine.setManufacturedDate(updateMedicineDto.getManufacturedDate());
        medicine.setExpirationDate(updateMedicineDto.getExpirationDate());

        // Set category and brand as null if IDs are not provided
        if (updateMedicineDto.getMedicineGroupId() != null) {
            MedicineGroup medicineGroup = groupRepository.findById(updateMedicineDto.getMedicineGroupId())
                    .orElseThrow(() -> new ResourceNotFoundException("Medicine does not exist"));
            medicine.setMedicine_group(medicineGroup);
        }

        if (updateMedicineDto.getBrandId() != null) {
            Brand brand = brandRepository.findById(updateMedicineDto.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException("Brand does not exist"));
            medicine.setBrand(brand);
        }

        return medicine;
    }

    public static Medicine mapToMedicine(MedicineDto medicineDto, GroupRepository groupRepository,
            BrandRepository brandRepository) {
        Medicine medicine = new Medicine();
        medicine.setId(medicineDto.getId());
        medicine.setName(medicineDto.getName());
        medicine.setDescription(medicineDto.getDescription());
        medicine.setCostPrice(medicineDto.getCostPrice());
        medicine.setSellingPrice(medicineDto.getSellingPrice());
        medicine.setSideEffect(medicineDto.getSideEffect());
        medicine.setHowToUse(medicineDto.getHowToUse());
        medicine.setSellingPrice(medicineDto.getSellingPrice());
        medicine.setManufacturedDate(medicineDto.getManufacturedDate());
        medicine.setExpirationDate(medicineDto.getExpirationDate());

        // Set category and brand as null if IDs are not provided
        if (medicineDto.getMedicineGroupId() != null) {
            MedicineGroup medicineGroup = groupRepository.findById(medicineDto.getMedicineGroupId())
                    .orElseThrow(() -> new ResourceNotFoundException("Medicine does not exist"));
            medicine.setMedicine_group(medicineGroup);
        }

        if (medicineDto.getBrandId() != null) {
            Brand brand = brandRepository.findById(medicineDto.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException("Brand does not exist"));
            medicine.setBrand(brand);
        }

        return medicine;
    }
}
