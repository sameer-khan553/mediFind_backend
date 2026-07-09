package com.sameer.medifind_backend.inventory.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    private UUID id;

    private String pharmacyName;

    private String medicineName;

    private String batchNumber;

    private LocalDate expiryDate;

    private Integer quantity;

    private BigDecimal purchasePrice;

    private BigDecimal sellingPrice;

    private Double discountPercentage;

    private String status;
}