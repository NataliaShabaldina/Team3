package com.example.bus_booking.services;

import com.example.bus_booking.entities.Notification;
import com.example.bus_booking.enums.NotificationMethod;
import com.example.bus_booking.enums.NotificationType;
import com.example.bus_booking.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> getActiveNotifications() {
        return notificationRepository.findActiveNotifications();
    }

    public List<Notification> getNotificationsByClientAndType(Long clientId, NotificationType notificationType) {
        return notificationRepository.findByClientIdAndNotificationType(clientId, notificationType);
    }

    public List<Notification> getNotificationsByMethod(NotificationMethod method) {
        return notificationRepository.findByNotificationMethod(method);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
