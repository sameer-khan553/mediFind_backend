package com.sameer.medifind_backend.pharmacy.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePharmacyRequest {

    @NotBlank
    private String name;

    private String gstNumber;

    @NotBlank
    private String ownerName;

    @NotBlank
    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String ownerPhone;

    @Email
    @NotBlank
    private String email;

    private String description;

    @NotNull
    private LocalTime openingTime;

    @NotNull
    private LocalTime closingTime;

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String area;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String pincode;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
