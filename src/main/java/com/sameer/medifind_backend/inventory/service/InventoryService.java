package com.sameer.medifind_backend.inventory.service;

import com.sameer.medifind_backend.inventory.dto.request.CreateInventoryRequest;
import com.sameer.medifind_backend.inventory.dto.request.UpdateInventoryRequest;
import com.sameer.medifind_backend.inventory.dto.response.InventoryResponse;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    InventoryResponse create(CreateInventoryRequest request);

    InventoryResponse update(UUID id, UpdateInventoryRequest request);

    InventoryResponse getById(UUID id);

    List<InventoryResponse> getAll();

    List<InventoryResponse> getByPharmacy(UUID pharmacyId);

    List<InventoryResponse> getByMedicine(UUID medicineId);

    void delete(UUID id);

}