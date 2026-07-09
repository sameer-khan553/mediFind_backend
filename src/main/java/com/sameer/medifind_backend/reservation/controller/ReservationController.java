package com.sameer.medifind_backend.reservation.controller;

import com.sameer.medifind_backend.reservation.dto.request.CreateReservationRequest;
import com.sameer.medifind_backend.reservation.dto.response.ReservationResponse;
import com.sameer.medifind_backend.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> reserve(
            @Valid @RequestBody CreateReservationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.reserve(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                reservationService.getById(id)
        );
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReservationResponse>> myReservations() {

        return ResponseEntity.ok(
                reservationService.getMyReservations()
        );
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponse> cancel(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                reservationService.cancel(id)
        );
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<ReservationResponse> confirm(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                reservationService.confirm(id)
        );
    }

    @PutMapping("/{id}/collect")
    public ResponseEntity<ReservationResponse> collect(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                reservationService.collect(id)
        );
    }
}