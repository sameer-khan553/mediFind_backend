package com.sameer.medifind_backend.pharmacy.entity;

import com.sameer.medifind_backend.common.entity.BaseEntity;
import com.sameer.medifind_backend.pharmacy.enums.PharmacyStatus;
import com.sameer.medifind_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pharmacies")
public class Pharmacy extends BaseEntity {

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String licenseNumber;

    @Column(unique = true, length = 30)
    private String gstNumber;

    @Column(nullable = false, length = 100)
    private String ownerName;

    @Column(nullable = false, unique = true, length = 15)
    private String ownerPhone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private LocalTime openingTime;

    @Column(nullable = false)
    private LocalTime closingTime;

    @Builder.Default
    @Column(nullable = false)
    private Boolean verified = false;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private PharmacyStatus status = PharmacyStatus.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
