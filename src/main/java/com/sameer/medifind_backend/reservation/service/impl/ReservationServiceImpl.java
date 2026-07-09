package com.sameer.medifind_backend.reservation.service.impl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.sameer.medifind_backend.exception.ResourceNotFoundException;
import com.sameer.medifind_backend.inventory.entity.Inventory;
import com.sameer.medifind_backend.inventory.repository.InventoryRepository;
import com.sameer.medifind_backend.reservation.dto.request.CreateReservationRequest;
import com.sameer.medifind_backend.reservation.dto.response.ReservationResponse;
import com.sameer.medifind_backend.reservation.entity.Reservation;
import com.sameer.medifind_backend.reservation.enums.ReservationStatus;
import com.sameer.medifind_backend.reservation.mapper.ReservationMapper;
import com.sameer.medifind_backend.reservation.repository.ReservationRepository;
import com.sameer.medifind_backend.reservation.service.ReservationService;
import com.sameer.medifind_backend.user.entity.User;
import com.sameer.medifind_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationResponse reserve(CreateReservationRequest request) {

        Authentication authentication =
                org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User customer = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Inventory inventory = inventoryRepository.findById(request.getInventoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory not found"));

        if (inventory.getQuantity() < request.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock available.");
        }

        inventory.setQuantity(
                inventory.getQuantity() - request.getQuantity()
        );

        inventoryRepository.save(inventory);

        Reservation reservation = Reservation.builder()
                .customer(customer)
                .inventory(inventory)
                .quantity(request.getQuantity())
                .reservedAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .status(ReservationStatus.PENDING)
                .build();

        return reservationMapper.toResponse(
                reservationRepository.save(reservation)
        );
    }

    @Override
    public ReservationResponse getById(UUID id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found"));

        return reservationMapper.toResponse(reservation);
    }

    @Override
    public List<ReservationResponse> getMyReservations() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User customer = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return reservationRepository.findByCustomerId(customer.getId())
                .stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    @Override
    public ReservationResponse cancel(UUID id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found"));

        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("Reservation cannot be cancelled.");
        }

        Inventory inventory = reservation.getInventory();

        inventory.setQuantity(
                inventory.getQuantity() + reservation.getQuantity()
        );

        inventoryRepository.save(inventory);

        reservation.setStatus(ReservationStatus.CANCELLED);

        return reservationMapper.toResponse(
                reservationRepository.save(reservation)
        );
    }

    @Override
    public ReservationResponse confirm(UUID id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found"));

        reservation.setStatus(ReservationStatus.CONFIRMED);

        return reservationMapper.toResponse(
                reservationRepository.save(reservation)
        );
    }

    @Override
    public ReservationResponse collect(UUID id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found"));

        reservation.setStatus(ReservationStatus.COLLECTED);

        return reservationMapper.toResponse(
                reservationRepository.save(reservation)
        );
    }
}