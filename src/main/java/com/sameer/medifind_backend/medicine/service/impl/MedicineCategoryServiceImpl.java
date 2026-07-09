package com.sameer.medifind_backend.medicine.service.impl;

import com.sameer.medifind_backend.exception.ResourceAlreadyExistsException;
import com.sameer.medifind_backend.exception.ResourceNotFoundException;
import com.sameer.medifind_backend.medicine.dto.request.CreateCategoryRequest;
import com.sameer.medifind_backend.medicine.dto.request.UpdateCategoryRequest;
import com.sameer.medifind_backend.medicine.dto.response.CategoryResponse;
import com.sameer.medifind_backend.medicine.entity.MedicineCategory;
import com.sameer.medifind_backend.medicine.mapper.MedicineCategoryMapper;
import com.sameer.medifind_backend.medicine.repository.MedicineCategoryRepository;
import com.sameer.medifind_backend.medicine.service.MedicineCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicineCategoryServiceImpl implements MedicineCategoryService {

    private final MedicineCategoryRepository repository;
    private final MedicineCategoryMapper mapper;

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {

        if (repository.existsByNameIgnoreCase(request.getName())) {
            throw new ResourceAlreadyExistsException("Category already exists");
        }

        MedicineCategory category = MedicineCategory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return mapper.toResponse(repository.save(category));
    }

    @Override
    public CategoryResponse update(UUID id, UpdateCategoryRequest request) {

        MedicineCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return mapper.toResponse(repository.save(category));
    }

    @Override
    public CategoryResponse getById(UUID id) {

        MedicineCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        return mapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        MedicineCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        repository.delete(category);
    }
}