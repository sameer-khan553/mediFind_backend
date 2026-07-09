package com.sameer.medifind_backend.medicine.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineResponse {

    private UUID id;

    private String name;

    private String genericName;

    private String sku;

    private String barcode;

    private String category;

    private String manufacturer;

    private String brand;

    private String dosageForm;

    private String strength;

    private String packSize;

    private BigDecimal mrp;

    private Boolean prescriptionRequired;

    private String storageCondition;

    private String status;
}