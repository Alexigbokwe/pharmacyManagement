package com.pharmacy.management.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.management.pms.entities.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}