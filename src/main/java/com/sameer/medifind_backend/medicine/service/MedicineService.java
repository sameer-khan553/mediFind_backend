package com.sameer.medifind_backend.medicine.service;

import com.sameer.medifind_backend.medicine.dto.request.CreateMedicineRequest;
import com.sameer.medifind_backend.medicine.dto.response.MedicineResponse;

import java.util.List;
import java.util.UUID;

public interface MedicineService {

    MedicineResponse create(CreateMedicineRequest request);

    MedicineResponse getById(UUID id);

    List<MedicineResponse> getAll();

    void delete(UUID id);

}