package com.sameer.medifind_backend.medicine.service;

import com.sameer.medifind_backend.medicine.dto.request.CreateBrandRequest;
import com.sameer.medifind_backend.medicine.dto.response.BrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {

    BrandResponse create(CreateBrandRequest request);

    BrandResponse getById(UUID id);

    List<BrandResponse> getAll();

    void delete(UUID id);

}