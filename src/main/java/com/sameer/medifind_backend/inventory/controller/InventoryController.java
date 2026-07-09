package com.sameer.medifind_backend.inventory.controller;

import com.sameer.medifind_backend.inventory.dto.request.CreateInventoryRequest;
import com.sameer.medifind_backend.inventory.dto.request.UpdateInventoryRequest;
import com.sameer.medifind_backend.inventory.dto.response.InventoryResponse;
import com.sameer.medifind_backend.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> create(
            @Valid @RequestBody CreateInventoryRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventoryService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateInventoryRequest request) {

        return ResponseEntity.ok(inventoryService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(inventoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAll() {

        return ResponseEntity.ok(inventoryService.getAll());
    }

    @GetMapping("/pharmacy/{pharmacyId}")
    public ResponseEntity<List<InventoryResponse>> getByPharmacy(
            @PathVariable UUID pharmacyId) {

        return ResponseEntity.ok(inventoryService.getByPharmacy(pharmacyId));
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<InventoryResponse>> getByMedicine(
            @PathVariable UUID medicineId) {

        return ResponseEntity.ok(inventoryService.getByMedicine(medicineId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id) {

        inventoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}