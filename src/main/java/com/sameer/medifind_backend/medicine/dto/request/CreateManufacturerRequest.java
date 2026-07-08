package com.sameer.medifind_backend.medicine.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateManufacturerRequest {

    @NotBlank
    private String name;

    private String country;

    private String website;

    private String email;

    private String contactNumber;

}