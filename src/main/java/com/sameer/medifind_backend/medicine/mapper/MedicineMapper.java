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
                .genericName(medicine.getGenericName())
                .sku(medicine.getSku())
                .barcode(medicine.getBarcode())
                .category(medicine.getCategory().getName())
                .manufacturer(medicine.getBrand().getManufacturer().getName())
                .brand(medicine.getBrand().getName())
                .dosageForm(medicine.getDosageForm().name())
                .strength(medicine.getStrength())
                .packSize(medicine.getPackSize())
                .mrp(medicine.getMrp())
                .prescriptionRequired(medicine.getPrescriptionRequired())
                .storageCondition(medicine.getStorageCondition())
                .status(medicine.getStatus().name())
                .build();
    }
}