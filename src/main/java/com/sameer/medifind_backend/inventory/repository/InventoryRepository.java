package com.sameer.medifind_backend.inventory.repository;

import com.sameer.medifind_backend.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InventoryRepository
        extends JpaRepository<Inventory, UUID> {

    boolean existsByBatchNumber(String batchNumber);

    List<Inventory> findByPharmacyId(UUID pharmacyId);

    List<Inventory> findByMedicineId(UUID medicineId);

}