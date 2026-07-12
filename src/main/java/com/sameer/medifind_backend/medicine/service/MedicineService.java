package com.sameer.medifind_backend.medicine.service;

import com.sameer.medifind_backend.medicine.dto.request.CreateMedicineRequest;
import com.sameer.medifind_backend.medicine.dto.request.UpdateMedicineRequest;
import com.sameer.medifind_backend.medicine.dto.response.MedicineResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface MedicineService {

    MedicineResponse create(CreateMedicineRequest request);

    MedicineResponse update(UUID id, UpdateMedicineRequest request);

    MedicineResponse getById(UUID id);

    Page<MedicineResponse> getAll(int page, int size);

    void delete(UUID id);

}