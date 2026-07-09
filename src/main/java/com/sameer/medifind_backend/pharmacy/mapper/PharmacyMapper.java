package com.sameer.medifind_backend.pharmacy.mapper;

import com.sameer.medifind_backend.pharmacy.dto.response.PharmacyResponse;
import com.sameer.medifind_backend.pharmacy.entity.Pharmacy;
import org.springframework.stereotype.Component;

@Component
public class PharmacyMapper {

    public PharmacyResponse toResponse(Pharmacy pharmacy) {

        return PharmacyResponse.builder()
                .id(pharmacy.getId())
                .name(pharmacy.getName())
                .licenseNumber(pharmacy.getLicenseNumber())
                .ownerName(pharmacy.getOwnerName())
                .ownerPhone(pharmacy.getOwnerPhone())
                .email(pharmacy.getEmail())
                .description(pharmacy.getDescription())
                .openingTime(pharmacy.getOpeningTime())
                .closingTime(pharmacy.getClosingTime())
                .verified(pharmacy.getVerified())
                .status(pharmacy.getStatus().name())
                .city(pharmacy.getAddress().getCity())
                .state(pharmacy.getAddress().getState())
                .country(pharmacy.getAddress().getCountry())
                .build();
    }
}
