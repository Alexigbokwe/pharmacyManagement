package com.pharmacy.management.pms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pharmacy.management.pms.entities.Brand;
import com.pharmacy.management.pms.entities.MedicineGroup;

public interface GroupRepository extends JpaRepository<MedicineGroup, Integer> {
    @Query("SELECT g FROM MedicineGroup g WHERE g.name = :name")
    Optional<Brand> findByName(@Param("name") String name);
}