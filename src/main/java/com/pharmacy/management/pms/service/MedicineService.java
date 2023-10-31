package com.pharmacy.management.pms.service;

import java.util.List;

import com.pharmacy.management.pms.dto.MedicineDto;
import com.pharmacy.management.pms.dto.UpdateMedicineDto;

public interface MedicineService {
    MedicineDto createMedicine(MedicineDto medicineDto);

    MedicineDto updateBrand(UpdateMedicineDto reqMedicine);

    List<MedicineDto> getAllMedicines();

    MedicineDto getMedicine(Integer medicineId);

    void deleteMedicine(Integer medicineId);
}
