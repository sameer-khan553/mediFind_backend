package com.sameer.medifind_backend.medicine.controller;

import com.sameer.medifind_backend.medicine.dto.request.CreateBrandRequest;
import com.sameer.medifind_backend.medicine.dto.response.BrandResponse;
import com.sameer.medifind_backend.medicine.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandResponse> create(
            @Valid @RequestBody CreateBrandRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(brandService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getById(@PathVariable UUID id) {

        return ResponseEntity.ok(brandService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAll() {

        return ResponseEntity.ok(brandService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        brandService.delete(id);

        return ResponseEntity.noContent().build();
    }
}