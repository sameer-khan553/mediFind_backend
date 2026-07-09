package com.sameer.medifind_backend.pharmacy.controller;

import com.sameer.medifind_backend.pharmacy.dto.request.RegisterPharmacyRequest;
import com.sameer.medifind_backend.pharmacy.dto.request.UpdatePharmacyRequest;
import com.sameer.medifind_backend.pharmacy.dto.response.PharmacyResponse;
import com.sameer.medifind_backend.pharmacy.service.PharmacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pharmacies")
@RequiredArgsConstructor
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @PostMapping
    public ResponseEntity<PharmacyResponse> register(
            @Valid @RequestBody RegisterPharmacyRequest request,
            Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pharmacyService.register(request, authentication));
    }

    @GetMapping("/me")
    public ResponseEntity<PharmacyResponse> getMyPharmacy(
            Authentication authentication) {

        return ResponseEntity.ok(
                pharmacyService.getMyPharmacy(authentication)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<PharmacyResponse> update(

            @PathVariable UUID id,

            @Valid
            @RequestBody
            UpdatePharmacyRequest request) {

        return ResponseEntity.ok(
                pharmacyService.update(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        pharmacyService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PharmacyResponse> getById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                pharmacyService.getById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<PharmacyResponse>> getAll() {

        return ResponseEntity.ok(
                pharmacyService.getAll()
        );
    }

    @PatchMapping("/{id}/verify")
    public ResponseEntity<PharmacyResponse> verify(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                pharmacyService.verify(id)
        );
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<PharmacyResponse>> searchByCity(
            @PathVariable String city) {

        return ResponseEntity.ok(
                pharmacyService.searchByCity(city)
        );
    }

    @GetMapping("/pincode/{pincode}")
    public ResponseEntity<List<PharmacyResponse>> searchByPincode(
            @PathVariable String pincode) {

        return ResponseEntity.ok(
                pharmacyService.searchByPincode(pincode)
        );
    }
}
