package com.sameer.medifind_backend.medicine.service.impl;
import com.sameer.medifind_backend.exception.ResourceAlreadyExistsException;
import com.sameer.medifind_backend.exception.ResourceNotFoundException;
import com.sameer.medifind_backend.medicine.dto.request.CreateMedicineRequest;
import com.sameer.medifind_backend.medicine.dto.request.UpdateMedicineRequest;
import com.sameer.medifind_backend.medicine.dto.response.MedicineResponse;
import com.sameer.medifind_backend.medicine.entity.Brand;
import com.sameer.medifind_backend.medicine.entity.Medicine;
import com.sameer.medifind_backend.medicine.entity.MedicineCategory;
import com.sameer.medifind_backend.medicine.mapper.MedicineMapper;
import com.sameer.medifind_backend.medicine.repository.BrandRepository;
import com.sameer.medifind_backend.medicine.repository.MedicineCategoryRepository;
import com.sameer.medifind_backend.medicine.repository.MedicineRepository;
import com.sameer.medifind_backend.medicine.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineCategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final MedicineMapper medicineMapper;

    public MedicineResponse create(CreateMedicineRequest request) {

        if (medicineRepository.existsBySku(request.getSku())) {
            throw new ResourceAlreadyExistsException("SKU already exists.");
        }

        if (medicineRepository.existsByBarcode(request.getBarcode())) {
            throw new ResourceAlreadyExistsException("Barcode already exists.");
        }

        MedicineCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Brand not found"));

        Medicine medicine = Medicine.builder()
                .name(request.getName())
                .genericName(request.getGenericName())
                .sku(request.getSku())
                .barcode(request.getBarcode())
                .category(category)
                .brand(brand)
                .dosageForm(request.getDosageForm())
                .strength(request.getStrength())
                .packSize(request.getPackSize())
                .mrp(request.getMrp())
                .prescriptionRequired(request.getPrescriptionRequired())
                .storageCondition(request.getStorageCondition())
                .build();

        return medicineMapper.toResponse(medicineRepository.save(medicine));
    }


    @Override
    public MedicineResponse getById(UUID id) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Medicine not found"));

        return medicineMapper.toResponse(medicine);
    }

    @Override
    public Page<MedicineResponse> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return medicineRepository
                .findAll(pageable)
                .map(medicineMapper::toResponse);
    }

    @Override
    public void delete(UUID id) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Medicine not found"));

        medicineRepository.delete(medicine);
    }


    @Override
    public MedicineResponse update(UUID id, UpdateMedicineRequest request) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Medicine not found"));

        MedicineCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Brand not found"));

        // Optional: Prevent duplicate barcode if it is changed
        if (!medicine.getBarcode().equals(request.getBarcode())
                && medicineRepository.existsByBarcode(request.getBarcode())) {
            throw new ResourceAlreadyExistsException("Barcode already exists.");
        }

        medicine.setName(request.getName());
        medicine.setGenericName(request.getGenericName());
        medicine.setBarcode(request.getBarcode());
        medicine.setCategory(category);
        medicine.setBrand(brand);
        medicine.setDosageForm(request.getDosageForm());
        medicine.setStrength(request.getStrength());
        medicine.setPackSize(request.getPackSize());
        medicine.setMrp(request.getMrp());
        medicine.setPrescriptionRequired(request.getPrescriptionRequired());
        medicine.setStorageCondition(request.getStorageCondition());

        Medicine updatedMedicine = medicineRepository.save(medicine);

        return medicineMapper.toResponse(updatedMedicine);
    }
}