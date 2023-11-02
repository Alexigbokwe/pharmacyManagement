package com.pharmacy.management.pms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.management.pms.dto.MedicineDto;
import com.pharmacy.management.pms.dto.UpdateMedicineDto;
import com.pharmacy.management.pms.service.MedicineService;
import com.pharmacy.management.pms.utils.SuccessResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/medicines")
public class MedicineController extends BaseController {
    private MedicineService medicineService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<MedicineDto>>> getAllMedicines() {
        List<MedicineDto> medicines = this.medicineService.getAllMedicines();
        return ResponseEntity.ok(this.successResponse("Medicines successfully fetched", medicines));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{id}")
    public ResponseEntity<SuccessResponse<MedicineDto>> getMedicine(@PathVariable("id") Integer medicineId) {
        MedicineDto medicine = this.medicineService.getMedicine(medicineId);
        return ResponseEntity.ok(this.successResponse("Medicine successfully fetched", medicine));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<SuccessResponse<MedicineDto>> saveMedicine(@Valid @RequestBody MedicineDto medicine) {
        MedicineDto createdMedicine = this.medicineService.createMedicine(medicine);
        return ResponseEntity.ok(this.successResponse("Medicine successfully created", createdMedicine));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping
    public ResponseEntity<SuccessResponse<MedicineDto>> updateMedicine(@Valid @RequestBody UpdateMedicineDto medicine) {
        MedicineDto updatedMedicine = this.medicineService.updateMedicine(medicine);
        return ResponseEntity.ok(this.successResponse("Medicine successfully updated", updatedMedicine));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("{id}")
    public ResponseEntity<SuccessResponse<String>> deleteMedicine(@PathVariable("id") Integer medicineId) {
        this.medicineService.deleteMedicine(medicineId);
        return ResponseEntity.ok(this.successResponse("Medicine successfully deleted", null));
    }

}
