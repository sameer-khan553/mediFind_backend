package com.sameer.medifind_backend.medicine.service.impl;

import com.sameer.medifind_backend.exception.ResourceAlreadyExistsException;
import com.sameer.medifind_backend.exception.ResourceNotFoundException;
import com.sameer.medifind_backend.medicine.dto.request.CreateBrandRequest;
import com.sameer.medifind_backend.medicine.dto.response.BrandResponse;
import com.sameer.medifind_backend.medicine.entity.Brand;
import com.sameer.medifind_backend.medicine.entity.Manufacturer;
import com.sameer.medifind_backend.medicine.mapper.BrandMapper;
import com.sameer.medifind_backend.medicine.repository.BrandRepository;
import com.sameer.medifind_backend.medicine.repository.ManufacturerRepository;
import com.sameer.medifind_backend.medicine.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandResponse create(CreateBrandRequest request) {

        if (brandRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ResourceAlreadyExistsException("Brand already exists.");
        }

        Manufacturer manufacturer = manufacturerRepository.findById(request.getManufacturerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Manufacturer not found"));

        Brand brand = Brand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .manufacturer(manufacturer)
                .build();

        return brandMapper.toResponse(
                brandRepository.save(brand)
        );
    }

    @Override
    public BrandResponse getById(UUID id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Brand not found"));

        return brandMapper.toResponse(brand);
    }

    @Override
    public List<BrandResponse> getAll() {

        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Brand not found"));

        brandRepository.delete(brand);
    }
}