package com.sameer.medifind_backend.medicine.service.impl;

import com.sameer.medifind_backend.exception.ResourceAlreadyExistsException;
import com.sameer.medifind_backend.exception.ResourceNotFoundException;
import com.sameer.medifind_backend.medicine.dto.request.CreateManufacturerRequest;
import com.sameer.medifind_backend.medicine.dto.response.ManufacturerResponse;
import com.sameer.medifind_backend.medicine.entity.Manufacturer;
import com.sameer.medifind_backend.medicine.mapper.ManufacturerMapper;
import com.sameer.medifind_backend.medicine.repository.ManufacturerRepository;
import com.sameer.medifind_backend.medicine.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;

    @Override
    public ManufacturerResponse create(CreateManufacturerRequest request) {

        if (manufacturerRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ResourceAlreadyExistsException("Manufacturer already exists.");
        }

        Manufacturer manufacturer = Manufacturer.builder()
                .name(request.getName())
                .country(request.getCountry())
                .website(request.getWebsite())
                .email(request.getEmail())
                .contactNumber(request.getContactNumber())
                .build();

        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);

        return manufacturerMapper.toResponse(savedManufacturer);
    }

    @Override
    public ManufacturerResponse getById(UUID id) {

        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Manufacturer not found with id: " + id));

        return manufacturerMapper.toResponse(manufacturer);
    }

    @Override
    public List<ManufacturerResponse> getAll() {

        return manufacturerRepository.findAll()
                .stream()
                .map(manufacturerMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Manufacturer not found with id: " + id));

        manufacturerRepository.delete(manufacturer);
    }
}