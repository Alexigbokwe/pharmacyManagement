package com.pharmacy.management.pms.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pharmacy.management.pms.dto.MedicineDto;
import com.pharmacy.management.pms.dto.UpdateMedicineDto;
import com.pharmacy.management.pms.entities.Medicine;
import com.pharmacy.management.pms.exception.ResourceAlreadyExistException;
import com.pharmacy.management.pms.exception.ResourceNotFoundException;
import com.pharmacy.management.pms.mappers.MedicineDataMapper;
import com.pharmacy.management.pms.repository.BrandRepository;
import com.pharmacy.management.pms.repository.GroupRepository;
import com.pharmacy.management.pms.repository.MedicineRepository;
import com.pharmacy.management.pms.service.MedicineService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private MedicineRepository medicineRepository;
    private GroupRepository groupRepository;
    private BrandRepository brandRepository;

    @Override
    public MedicineDto createMedicine(MedicineDto medicineDto) {
        if (this.doesMedicineExistByName(medicineDto.getName())) {
            throw new ResourceAlreadyExistException(
                    "Medicine with the name " + medicineDto.getName() + " already exists.");
        }
        Medicine medicine = MedicineDataMapper.mapToMedicine(medicineDto, groupRepository, brandRepository);
        return MedicineDataMapper.mapToMedicineDto(this.medicineRepository.save(medicine));
    }

    @Override
    public MedicineDto updateMedicine(UpdateMedicineDto reqMedicine) {

        if (!this.doesMedicineExistById(reqMedicine.getId())) {
            throw new ResourceNotFoundException("Medicine not found");
        }
        Medicine medicine = MedicineDataMapper.mapToMedicine(reqMedicine, groupRepository, brandRepository);
        return MedicineDataMapper.mapToMedicineDto(this.medicineRepository.save(medicine));
    }

    @Override
    public List<MedicineDto> getAllMedicines() {
        List<Medicine> medicines = this.medicineRepository.findAll();
        if (medicines.isEmpty()) {
            throw new ResourceNotFoundException("No medicines found in the system");
        }
        return medicines.stream().map(medicine -> MedicineDataMapper.mapToMedicineDto(medicine))
                .collect(Collectors.toList());
    }

    @Override
    public MedicineDto getMedicine(Integer medicineId) {
        Medicine medicine = this.medicineRepository.findById(medicineId)
                .orElseThrow(() -> new ResourceNotFoundException("No record found"));
        return MedicineDataMapper.mapToMedicineDto(medicine);
    }

    @Override
    public void deleteMedicine(Integer medicineId) {
        this.medicineRepository.deleteById(medicineId);
    }

    private Boolean doesMedicineExistByName(String medicineName) {
        return this.medicineRepository.findByName(medicineName).isPresent();
    }

    private Boolean doesMedicineExistById(Integer id) {
        return this.medicineRepository.findById(id).isPresent();
    }
}
