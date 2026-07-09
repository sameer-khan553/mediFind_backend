package com.sameer.medifind_backend.dashboard.service.impl;

import com.sameer.medifind_backend.dashboard.dto.DashboardResponse;
import com.sameer.medifind_backend.dashboard.service.DashboardService;
import com.sameer.medifind_backend.inventory.repository.InventoryRepository;
import com.sameer.medifind_backend.medicine.repository.MedicineRepository;
import com.sameer.medifind_backend.pharmacy.repository.PharmacyRepository;
import com.sameer.medifind_backend.reservation.enums.ReservationStatus;
import com.sameer.medifind_backend.reservation.repository.ReservationRepository;
import com.sameer.medifind_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final InventoryRepository inventoryRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()
                .totalUsers(userRepository.count())
                .totalPharmacies(pharmacyRepository.count())
                .totalMedicines(medicineRepository.count())
                .totalInventory(inventoryRepository.count())
                .totalReservations(reservationRepository.count())
                .activeReservations(
                        reservationRepository.countByStatus(
                                ReservationStatus.PENDING
                        )
                )
                .expiredReservations(
                        reservationRepository.countByStatus(
                                ReservationStatus.EXPIRED
                        )
                )
                .build();
    }
}