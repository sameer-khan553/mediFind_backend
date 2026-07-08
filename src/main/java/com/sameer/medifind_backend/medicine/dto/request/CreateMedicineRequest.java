package com.sameer.medifind_backend.medicine.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMedicineRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    @NotBlank
    private String genericName;

    @NotBlank
    private String brandName;

    @NotBlank
    private String dosageForm;

    @NotBlank
    private String strength;

    @NotNull
    private Boolean prescriptionRequired;

    @NotBlank
    private String storageCondition;

    @NotNull
    private BigDecimal mrp;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID manufacturerId;

}
