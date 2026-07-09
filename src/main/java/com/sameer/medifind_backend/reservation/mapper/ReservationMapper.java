package com.sameer.medifind_backend.reservation.mapper;

import com.sameer.medifind_backend.reservation.dto.response.ReservationResponse;
import com.sameer.medifind_backend.reservation.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationResponse toResponse(Reservation reservation) {

        return ReservationResponse.builder()
                .id(reservation.getId())
                .customer(reservation.getCustomer().getFirstName())
                .pharmacy(reservation.getInventory().getPharmacy().getName())
                .medicine(reservation.getInventory().getMedicine().getName())
                .quantity(reservation.getQuantity())
                .status(reservation.getStatus().name())
                .reservedAt(reservation.getReservedAt())
                .expiresAt(reservation.getExpiresAt())
                .build();
    }
}