package com.sameer.medifind_backend.medicine.mapper;

import com.sameer.medifind_backend.medicine.dto.response.BrandResponse;
import com.sameer.medifind_backend.medicine.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public BrandResponse toResponse(Brand brand) {

        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .manufacturer(brand.getManufacturer().getName())
                .build();
    }
}