package com.sameer.medifind_backend.medicine.repository;

import com.sameer.medifind_backend.medicine.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Brand> findByNameIgnoreCase(String name);

    List<Brand> findByManufacturerId(UUID manufacturerId);

}