package com.sameer.medifind_backend.medicine.entity;

import com.sameer.medifind_backend.common.entity.BaseEntity;
import com.sameer.medifind_backend.medicine.enums.DosageForm;
import com.sameer.medifind_backend.medicine.enums.MedicineStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "medicines")
public class Medicine extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String genericName;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false, unique = true)
    private String barcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MedicineCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private DosageForm dosageForm;

    private String strength;

    private String packSize;

    private BigDecimal mrp;

    private Boolean prescriptionRequired;

    private String storageCondition;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MedicineStatus status = MedicineStatus.ACTIVE;

}