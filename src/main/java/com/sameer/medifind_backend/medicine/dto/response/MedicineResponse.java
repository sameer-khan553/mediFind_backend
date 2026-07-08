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

    private String sku;

    private String genericName;

    private String brandName;

    private String dosageForm;

    private String strength;

    private Boolean prescriptionRequired;

    private String storageCondition;

    private BigDecimal mrp;

    private String category;

    private String manufacturer;

}