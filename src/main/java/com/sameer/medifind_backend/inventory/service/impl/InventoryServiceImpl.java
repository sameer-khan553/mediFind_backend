package com.sameer.medifind_backend.inventory.service.impl;

import com.sameer.medifind_backend.exception.ResourceAlreadyExistsException;
import com.sameer.medifind_backend.exception.ResourceNotFoundException;
import com.sameer.medifind_backend.inventory.dto.request.CreateInventoryRequest;
import com.sameer.medifind_backend.inventory.dto.request.UpdateInventoryRequest;
import com.sameer.medifind_backend.inventory.dto.response.InventoryResponse;
import com.sameer.medifind_backend.inventory.entity.Inventory;
import com.sameer.medifind_backend.inventory.mapper.InventoryMapper;
import com.sameer.medifind_backend.inventory.repository.InventoryRepository;
import com.sameer.medifind_backend.inventory.service.InventoryService;
import com.sameer.medifind_backend.medicine.entity.Medicine;
import com.sameer.medifind_backend.medicine.repository.MedicineRepository;
import com.sameer.medifind_backend.pharmacy.entity.Pharmacy;
import com.sameer.medifind_backend.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final InventoryMapper inventoryMapper;


    @Override
    public InventoryResponse create(CreateInventoryRequest request) {

        if (inventoryRepository.existsByBatchNumber(request.getBatchNumber())) {
            throw new ResourceAlreadyExistsException("Batch number already exists");
        }

        Pharmacy pharmacy = pharmacyRepository.findById(request.getPharmacyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pharmacy not found"));

        Medicine medicine = medicineRepository.findById(request.getMedicineId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Medicine not found"));

        Inventory inventory = Inventory.builder()
                .pharmacy(pharmacy)
                .medicine(medicine)
                .batchNumber(request.getBatchNumber())
                .expiryDate(request.getExpiryDate())
                .quantity(request.getQuantity())
                .purchasePrice(request.getPurchasePrice())
                .sellingPrice(request.getSellingPrice())
                .discountPercentage(request.getDiscountPercentage())
                .build();

        return inventoryMapper.toResponse(
                inventoryRepository.save(inventory)
        );
    }

    @Override
    public InventoryResponse update(UUID id, UpdateInventoryRequest request) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory not found"));

        inventory.setBatchNumber(request.getBatchNumber());
        inventory.setExpiryDate(request.getExpiryDate());
        inventory.setQuantity(request.getQuantity());
        inventory.setPurchasePrice(request.getPurchasePrice());
        inventory.setSellingPrice(request.getSellingPrice());
        inventory.setDiscountPercentage(request.getDiscountPercentage());

        return inventoryMapper.toResponse(
                inventoryRepository.save(inventory)
        );
    }

    @Override
    public InventoryResponse getById(UUID id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory not found"));

        return inventoryMapper.toResponse(inventory);
    }

    @Override
    public List<InventoryResponse> getAll() {

        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<InventoryResponse> getByPharmacy(UUID pharmacyId) {

        return inventoryRepository.findByPharmacyId(pharmacyId)
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<InventoryResponse> getByMedicine(UUID medicineId) {

        return inventoryRepository.findByMedicineId(medicineId)
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory not found"));

        inventoryRepository.delete(inventory);
    }
}