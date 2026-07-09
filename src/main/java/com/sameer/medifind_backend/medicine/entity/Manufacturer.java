package com.sameer.medifind_backend.medicine.entity;

import com.sameer.medifind_backend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "manufacturers")
public class Manufacturer extends BaseEntity {

    @Column(nullable = false, unique = true, length = 150)
    private String name;

    @Column(length = 100)
    private String country;

    @Column(length = 255)
    private String website;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String contactNumber;
}