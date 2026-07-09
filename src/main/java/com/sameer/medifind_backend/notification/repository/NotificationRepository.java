package com.sameer.medifind_backend.notification.repository;

import com.sameer.medifind_backend.notification.entity.Notification;
import com.sameer.medifind_backend.notification.enums.NotificationStatus;
import com.sameer.medifind_backend.notification.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByRecipientEmail(String recipientEmail);

    List<Notification> findByStatus(NotificationStatus status);

    List<Notification> findByType(NotificationType type);

    List<Notification> findByRecipientEmailAndStatus(
            String recipientEmail,
            NotificationStatus status
    );
}