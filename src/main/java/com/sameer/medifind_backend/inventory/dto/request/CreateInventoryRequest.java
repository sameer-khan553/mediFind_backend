package com.sameer.medifind_backend.inventory.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInventoryRequest {

    @NotNull
    private UUID pharmacyId;

    @NotNull
    private UUID medicineId;

    @NotBlank
    private String batchNumber;

    @NotNull
    private LocalDate expiryDate;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotNull
    private BigDecimal purchasePrice;

    @NotNull
    private BigDecimal sellingPrice;

    @NotNull
    private Double discountPercentage;
}