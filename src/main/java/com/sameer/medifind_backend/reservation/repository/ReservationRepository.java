package com.sameer.medifind_backend.reservation.repository;

import com.sameer.medifind_backend.reservation.entity.Reservation;
import com.sameer.medifind_backend.reservation.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<Reservation> findByCustomerId(UUID customerId);

    List<Reservation> findByStatus(ReservationStatus status);

    long countByStatus(ReservationStatus status);

}