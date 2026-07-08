package com.sameer.medifind_backend.medicine.service;

import com.sameer.medifind_backend.medicine.dto.request.CreateManufacturerRequest;
import com.sameer.medifind_backend.medicine.dto.response.ManufacturerResponse;

import java.util.List;
import java.util.UUID;

public interface ManufacturerService {

    ManufacturerResponse create(CreateManufacturerRequest request);

    ManufacturerResponse getById(UUID id);

    List<ManufacturerResponse> getAll();

    void delete(UUID id);

}