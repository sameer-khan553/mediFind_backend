package com.sameer.medifind_backend.medicine.service;

import com.sameer.medifind_backend.medicine.dto.request.CreateCategoryRequest;
import com.sameer.medifind_backend.medicine.dto.request.UpdateCategoryRequest;
import com.sameer.medifind_backend.medicine.dto.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface MedicineCategoryService {

    CategoryResponse create(CreateCategoryRequest request);

    CategoryResponse update(UUID id, UpdateCategoryRequest request);

    CategoryResponse getById(UUID id);

    List<CategoryResponse> getAll();

    void delete(UUID id);

}