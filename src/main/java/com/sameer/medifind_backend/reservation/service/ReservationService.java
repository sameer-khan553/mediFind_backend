package com.sameer.medifind_backend.reservation.service;

import com.sameer.medifind_backend.reservation.dto.request.CreateReservationRequest;
import com.sameer.medifind_backend.reservation.dto.response.ReservationResponse;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    ReservationResponse reserve(CreateReservationRequest request);

    ReservationResponse getById(UUID id);

    List<ReservationResponse> getMyReservations();

    ReservationResponse cancel(UUID id);

    ReservationResponse confirm(UUID id);

    ReservationResponse collect(UUID id);

}