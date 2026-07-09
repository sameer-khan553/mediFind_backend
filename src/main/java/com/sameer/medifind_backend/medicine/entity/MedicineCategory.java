package com.sameer.medifind_backend.medicine.entity;
import com.sameer.medifind_backend.common.entity.BaseEntity;
import com.sameer.medifind_backend.medicine.enums.MedicineCategoryStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "medicine_categories")
public class MedicineCategory extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private MedicineCategoryStatus status = MedicineCategoryStatus.ACTIVE;

}