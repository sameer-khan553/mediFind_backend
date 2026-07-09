package com.sameer.medifind_backend.medicine.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    private UUID id;

    private String name;

    private String description;

    private String manufacturer;

}