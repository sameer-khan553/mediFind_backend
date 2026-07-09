package com.sameer.medifind_backend.medicine.controller;

import com.sameer.medifind_backend.medicine.dto.request.CreateManufacturerRequest;
import com.sameer.medifind_backend.medicine.dto.response.ManufacturerResponse;
import com.sameer.medifind_backend.medicine.service.ManufacturerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @PostMapping
    public ResponseEntity<ManufacturerResponse> create(
            @Valid @RequestBody CreateManufacturerRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(manufacturerService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerResponse> getById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(manufacturerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerResponse>> getAll() {

        return ResponseEntity.ok(manufacturerService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id) {

        manufacturerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}