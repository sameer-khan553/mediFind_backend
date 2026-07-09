package com.sameer.medifind_backend.medicine.dto.request;

import com.sameer.medifind_backend.medicine.enums.DosageForm;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMedicineRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String genericName;

    @NotBlank
    private String barcode;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID brandId;

    @NotNull
    private DosageForm dosageForm;

    @NotBlank
    private String strength;

    @NotBlank
    private String packSize;

    @NotNull
    private BigDecimal mrp;

    @NotNull
    private Boolean prescriptionRequired;

    @NotBlank
    private String storageCondition;

}