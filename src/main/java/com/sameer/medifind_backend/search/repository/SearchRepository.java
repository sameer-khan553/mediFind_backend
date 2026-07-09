package com.sameer.medifind_backend.search.repository;

import com.sameer.medifind_backend.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SearchRepository extends JpaRepository<Inventory, UUID> {

    List<Inventory> findByMedicine_NameContainingIgnoreCase(String medicineName);

}