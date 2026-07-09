package com.sameer.medifind_backend.pharmacy.dto.response;

import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyResponse {

    private UUID id;

    private String name;

    private String licenseNumber;

    private String ownerName;

    private String ownerPhone;

    private String email;

    private String description;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private Boolean verified;

    private String status;

    private String city;

    private String state;

    private String country;
}
