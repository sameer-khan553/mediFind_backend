package com.sameer.medifind_backend.medicine.repository;


import com.sameer.medifind_backend.medicine.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Manufacturer> findById(UUID id);

    Optional<Manufacturer> findByNameIgnoreCase(String name);

}