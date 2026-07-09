package com.sameer.medifind_backend.notification.service;

public interface NotificationService {

    void sendReservationEmail(
            String email,
            String medicine,
            Integer quantity
    );

}