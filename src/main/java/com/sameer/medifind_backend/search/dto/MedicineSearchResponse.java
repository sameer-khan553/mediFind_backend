package com.sameer.medifind_backend.search.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineSearchResponse {

    private UUID inventoryId;

    private UUID medicineId;

    private String medicineName;

    private String genericName;

    private String brand;

    private String manufacturer;

    private String pharmacy;

    private Integer quantity;

    private BigDecimal sellingPrice;

    private Double discountPercentage;

    private Boolean available;

}