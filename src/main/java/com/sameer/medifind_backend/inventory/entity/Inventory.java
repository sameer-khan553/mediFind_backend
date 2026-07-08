package com.sameer.medifind_backend.inventory.entity;

import com.sameer.medifind_backend.common.entity.BaseEntity;
import com.sameer.medifind_backend.inventory.enums.InventoryStatus;
import com.sameer.medifind_backend.medicine.entity.Medicine;
import com.sameer.medifind_backend.pharmacy.entity.Pharmacy;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "inventory",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "pharmacy_id",
                                "medicine_id",
                                "batch_number"
                        }
                )
        }
)
public class Inventory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private Pharmacy pharmacy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @Column(nullable = false)
    private String batchNumber;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sellingPrice;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private InventoryStatus status = InventoryStatus.IN_STOCK;

}
