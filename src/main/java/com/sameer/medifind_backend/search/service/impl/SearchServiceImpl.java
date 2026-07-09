package com.sameer.medifind_backend.search.service.impl;

import com.sameer.medifind_backend.inventory.entity.Inventory;
import com.sameer.medifind_backend.search.dto.MedicineSearchResponse;
import com.sameer.medifind_backend.search.repository.SearchRepository;
import com.sameer.medifind_backend.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;

    private MedicineSearchResponse mapToResponse(Inventory inventory){

        return MedicineSearchResponse.builder()
                .inventoryId(inventory.getId())
                .medicineId(inventory.getMedicine().getId())
                .medicineName(inventory.getMedicine().getName())
                .genericName(inventory.getMedicine().getGenericName())
                .brand(inventory.getMedicine().getBrand().getName())
                .manufacturer(
                        inventory.getMedicine()
                                .getBrand()
                                .getManufacturer()
                                .getName()
                )
                .pharmacy(inventory.getPharmacy().getName())
                .quantity(inventory.getQuantity())
                .sellingPrice(inventory.getSellingPrice())
                .discountPercentage(inventory.getDiscountPercentage())
                .available(inventory.getQuantity() > 0)
                .build();
    }

    @Override
    public List<MedicineSearchResponse> searchByMedicine(String medicineName) {

        return searchRepository
                .findByMedicine_NameContainingIgnoreCase(medicineName)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public List<MedicineSearchResponse> searchByGenericName(String genericName) {

        return searchRepository
                .findByMedicine_GenericNameContainingIgnoreCase(genericName)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<MedicineSearchResponse> searchByBrand(String brand) {

        return searchRepository
                .findByMedicine_Brand_NameContainingIgnoreCase(brand)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<MedicineSearchResponse> searchByCategory(String category) {

        return searchRepository
                .findByMedicine_Category_NameContainingIgnoreCase(category)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}