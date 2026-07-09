package com.sameer.medifind_backend.medicine.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufacturerResponse {

    private UUID id;

    private String name;

    private String country;

    private String website;

    private String email;

    private String contactNumber;

}