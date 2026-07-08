package com.sameer.medifind_backend.medicine.repository;

import com.sameer.medifind_backend.medicine.entity.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicineCategoryRepository
        extends JpaRepository<MedicineCategory, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Optional<MedicineCategory> findByNameIgnoreCase(String name);

}