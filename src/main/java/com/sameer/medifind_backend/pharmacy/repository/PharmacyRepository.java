package com.sameer.medifind_backend.pharmacy.repository;

import com.sameer.medifind_backend.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PharmacyRepository extends JpaRepository<Pharmacy, UUID> {

    boolean existsByLicenseNumber(String licenseNumber);

    boolean existsByEmail(String email);

    boolean existsByOwnerPhone(String ownerPhone);

    Optional<Pharmacy> findByUserId(UUID userId);

}