package com.sameer.medifind_backend.medicine.mapper;

import com.sameer.medifind_backend.medicine.dto.response.ManufacturerResponse;
import com.sameer.medifind_backend.medicine.entity.Manufacturer;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMapper {

    public ManufacturerResponse toResponse(Manufacturer manufacturer) {

        return ManufacturerResponse.builder()
                .id(manufacturer.getId())
                .name(manufacturer.getName())
                .country(manufacturer.getCountry())
                .website(manufacturer.getWebsite())
                .email(manufacturer.getEmail())
                .contactNumber(manufacturer.getContactNumber())
                .build();
    }
}