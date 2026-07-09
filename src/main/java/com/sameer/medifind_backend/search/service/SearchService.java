package com.sameer.medifind_backend.search.service;

import com.sameer.medifind_backend.search.dto.MedicineSearchResponse;

import java.util.List;

public interface SearchService {

    List<MedicineSearchResponse> searchByMedicine(String medicineName);

}