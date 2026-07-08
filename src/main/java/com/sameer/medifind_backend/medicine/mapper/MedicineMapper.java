package com.sameer.medifind_backend.medicine.mapper;

import com.sameer.medifind_backend.medicine.dto.response.MedicineResponse;
import com.sameer.medifind_backend.medicine.entity.Medicine;
import org.springframework.stereotype.Component;

@Component
public class MedicineMapper {

    public MedicineResponse toResponse(Medicine medicine) {

        return MedicineResponse.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .sku(medicine.getSku())
                .genericName(medicine.getGenericName())
                .brandName(medicine.getBrandName())
                .dosageForm(medicine.getDosageForm())
                .strength(medicine.getStrength())
                .prescriptionRequired(medicine.getPrescriptionRequired())
                .storageCondition(medicine.getStorageCondition())
                .mrp(medicine.getMrp())
                .category(medicine.getCategory().getName())
                .manufacturer(medicine.getManufacturer().getName())
                .build();
    }
}