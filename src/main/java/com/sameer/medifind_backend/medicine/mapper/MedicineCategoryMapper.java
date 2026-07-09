package com.sameer.medifind_backend.medicine.mapper;

import com.sameer.medifind_backend.medicine.dto.response.CategoryResponse;
import com.sameer.medifind_backend.medicine.entity.MedicineCategory;
import org.springframework.stereotype.Component;

@Component
public class MedicineCategoryMapper {

    public CategoryResponse toResponse(MedicineCategory category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .status(category.getStatus().name())
                .build();
    }
}