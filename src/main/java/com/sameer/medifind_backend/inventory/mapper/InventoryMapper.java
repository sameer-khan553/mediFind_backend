package com.sameer.medifind_backend.inventory.mapper;

import com.sameer.medifind_backend.inventory.dto.response.InventoryResponse;
import com.sameer.medifind_backend.inventory.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryResponse toResponse(Inventory inventory){

        return InventoryResponse.builder()
                .id(inventory.getId())
                .pharmacyName(inventory.getPharmacy().getName())
                .medicineName(inventory.getMedicine().getName())
                .batchNumber(inventory.getBatchNumber())
                .expiryDate(inventory.getExpiryDate())
                .quantity(inventory.getQuantity())
                .purchasePrice(inventory.getPurchasePrice())
                .sellingPrice(inventory.getSellingPrice())
                .discountPercentage(inventory.getDiscountPercentage())
                .status(inventory.getStatus().name())
                .build();
    }
}