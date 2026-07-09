package com.sameer.medifind_backend.reservation.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

    private UUID id;

    private String customer;

    private String pharmacy;

    private String medicine;

    private Integer quantity;

    private String status;

    private LocalDateTime reservedAt;

    private LocalDateTime expiresAt;

}