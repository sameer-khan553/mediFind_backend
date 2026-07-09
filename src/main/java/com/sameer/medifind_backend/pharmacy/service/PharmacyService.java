package com.sameer.medifind_backend.pharmacy.service;


import com.sameer.medifind_backend.pharmacy.dto.request.RegisterPharmacyRequest;
import com.sameer.medifind_backend.pharmacy.dto.request.UpdatePharmacyRequest;
import com.sameer.medifind_backend.pharmacy.dto.response.PharmacyResponse;
import com.sameer.medifind_backend.pharmacy.entity.Pharmacy;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface PharmacyService {

    PharmacyResponse register(RegisterPharmacyRequest request,
                              Authentication authentication);


    PharmacyResponse getMyPharmacy(Authentication authentication);

    PharmacyResponse update(UUID id,
                            UpdatePharmacyRequest request);

    void delete(UUID id);

    PharmacyResponse getById(UUID id);

    List<PharmacyResponse> getAll();

    PharmacyResponse verify(UUID id);


    List<PharmacyResponse> searchByCity(String city);

    List<PharmacyResponse> searchByPincode(String pincode);

}