package com.sameer.medifind_backend.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private Long totalUsers;

    private Long totalPharmacies;

    private Long totalMedicines;

    private Long totalInventory;

    private Long totalReservations;

    private Long activeReservations;

    private Long expiredReservations;

}