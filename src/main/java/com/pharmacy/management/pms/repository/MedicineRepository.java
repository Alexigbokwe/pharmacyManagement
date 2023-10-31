package com.pharmacy.management.pms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pharmacy.management.pms.entities.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    @Query("SELECT m FROM Brand m WHERE m.name = :name")
    Optional<Medicine> findByName(@Param("name") String name);
}
