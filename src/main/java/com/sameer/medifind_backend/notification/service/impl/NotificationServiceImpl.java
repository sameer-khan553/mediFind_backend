package com.sameer.medifind_backend.notification.service.impl;

import com.sameer.medifind_backend.notification.entity.Notification;
import com.sameer.medifind_backend.notification.enums.NotificationStatus;
import com.sameer.medifind_backend.notification.enums.NotificationType;
import com.sameer.medifind_backend.notification.repository.NotificationRepository;
import com.sameer.medifind_backend.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {


    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;

    @Override
    public void sendReservationEmail(
            String email,
            String medicine,
            Integer quantity) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);

        message.setSubject("Medicine Reservation Confirmed");

        message.setText(
                "Your reservation has been created.\n\n" +
                        "Medicine : " + medicine + "\n" +
                        "Quantity : " + quantity + "\n\n" +
                        "Thank you for using MediFind."
        );

        mailSender.send(message);

        Notification notification = Notification.builder()
                .recipientEmail(email)
                .subject(message.getSubject())
                .message(message.getText())
                .type(NotificationType.EMAIL)
                .status(NotificationStatus.SENT)
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}