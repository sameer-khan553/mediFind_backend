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

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String genericName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DosageForm dosageForm;

    @Column(nullable = false, length = 50)
    private String strength;

    @Column(nullable = false)
    private Boolean prescriptionRequired;

    @Column(nullable = false)
    private String storageCondition;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal mrp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MedicineCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private MedicineStatus status = MedicineStatus.ACTIVE;

    @Column(nullable = false, unique = true)
    private String barcode;

    @Column(length = 20)
    private String hsnCode;

    @Column(nullable = false)
    private String packSize;

}