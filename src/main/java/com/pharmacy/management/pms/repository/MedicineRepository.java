package com.pharmacy.management.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.management.pms.entities.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

}
