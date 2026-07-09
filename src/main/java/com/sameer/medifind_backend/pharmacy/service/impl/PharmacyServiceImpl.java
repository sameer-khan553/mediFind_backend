package com.sameer.medifind_backend.pharmacy.service.impl;
import com.sameer.medifind_backend.exception.ResourceAlreadyExistsException;
import com.sameer.medifind_backend.exception.ResourceNotFoundException;
import com.sameer.medifind_backend.pharmacy.dto.request.RegisterPharmacyRequest;
import com.sameer.medifind_backend.pharmacy.dto.request.UpdatePharmacyRequest;
import com.sameer.medifind_backend.pharmacy.dto.response.PharmacyResponse;
import com.sameer.medifind_backend.pharmacy.entity.Address;
import com.sameer.medifind_backend.pharmacy.entity.Pharmacy;
import com.sameer.medifind_backend.pharmacy.mapper.PharmacyMapper;
import com.sameer.medifind_backend.pharmacy.repository.PharmacyRepository;
import com.sameer.medifind_backend.pharmacy.service.PharmacyService;
import com.sameer.medifind_backend.user.entity.User;
import com.sameer.medifind_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;
    private final PharmacyMapper pharmacyMapper;

    @Override
    public PharmacyResponse register(RegisterPharmacyRequest request,
                                     Authentication authentication) {

        if (pharmacyRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new ResourceAlreadyExistsException("License already exists");
        }

        if (pharmacyRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        if (pharmacyRepository.existsByOwnerPhone(request.getOwnerPhone())) {
            throw new ResourceAlreadyExistsException("Phone already exists");
        }

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Address address = Address.builder()
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .area(request.getArea())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .pincode(request.getPincode())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

        Pharmacy pharmacy = Pharmacy.builder()
                .name(request.getName())
                .licenseNumber(request.getLicenseNumber())
                .gstNumber(request.getGstNumber())
                .ownerName(request.getOwnerName())
                .ownerPhone(request.getOwnerPhone())
                .email(request.getEmail())
                .description(request.getDescription())
                .openingTime(request.getOpeningTime())
                .closingTime(request.getClosingTime())
                .address(address)
                .user(user)
                .build();

        Pharmacy saved = pharmacyRepository.save(pharmacy);

        return pharmacyMapper.toResponse(saved);
    }

    @Override
    public PharmacyResponse getMyPharmacy(Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Pharmacy pharmacy = pharmacyRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found"));

        return pharmacyMapper.toResponse(pharmacy);
    }



    @Override
    public PharmacyResponse update(UUID id,
                                   UpdatePharmacyRequest request) {

        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pharmacy not found"));

        pharmacy.setName(request.getName());
        pharmacy.setGstNumber(request.getGstNumber());
        pharmacy.setOwnerName(request.getOwnerName());
        pharmacy.setOwnerPhone(request.getOwnerPhone());
        pharmacy.setEmail(request.getEmail());
        pharmacy.setDescription(request.getDescription());
        pharmacy.setOpeningTime(request.getOpeningTime());
        pharmacy.setClosingTime(request.getClosingTime());

        Address address = pharmacy.getAddress();

        address.setAddressLine1(request.getAddressLine1());
        address.setAddressLine2(request.getAddressLine2());
        address.setArea(request.getArea());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setPincode(request.getPincode());
        address.setLatitude(request.getLatitude());
        address.setLongitude(request.getLongitude());

        Pharmacy updated = pharmacyRepository.save(pharmacy);

        return pharmacyMapper.toResponse(updated);
    }


    @Override
    public void delete(UUID id) {

        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pharmacy not found"));

        pharmacyRepository.delete(pharmacy);
    }

    @Override
    public PharmacyResponse getById(UUID id) {

        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pharmacy not found"));

        return pharmacyMapper.toResponse(pharmacy);
    }

    @Override
    public List<PharmacyResponse> getAll() {

        return pharmacyRepository.findAll()
                .stream()
                .map(pharmacyMapper::toResponse)
                .toList();
    }

    @Override
    public PharmacyResponse verify(UUID id) {

        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pharmacy not found"));

        pharmacy.setVerified(true);

        Pharmacy updated = pharmacyRepository.save(pharmacy);

        return pharmacyMapper.toResponse(updated);
    }

    @Override
    public List<PharmacyResponse> searchByCity(String city) {

        return pharmacyRepository
                .findByAddress_CityIgnoreCase(city)
                .stream()
                .map(pharmacyMapper::toResponse)
                .toList();
    }

    @Override
    public List<PharmacyResponse> searchByPincode(String pincode) {

        return pharmacyRepository
                .findByAddress_Pincode(pincode)
                .stream()
                .map(pharmacyMapper::toResponse)
                .toList();
    }
}
