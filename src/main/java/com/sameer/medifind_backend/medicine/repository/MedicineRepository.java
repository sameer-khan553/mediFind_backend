package com.sameer.medifind_backend.medicine.repository;

import com.sameer.medifind_backend.medicine.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {

    boolean existsBySku(String sku);

    boolean existsByBarcode(String barcode);

    List<Medicine> findByNameContainingIgnoreCase(String name);

}