package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Notification;
import com.example.bus_booking.enums.NotificationMethod;
import com.example.bus_booking.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT Notification FROM Notification WHERE notificationIs = true")
    List<Notification> findActiveNotifications();

    @Query("SELECT n FROM Notification n WHERE n.client.id = :clientId AND n.notificationType = :notificationType")
    List<Notification> findByClientIdAndNotificationType(@Param("clientId") Long clientId, @Param("notificationType") NotificationType notificationType);

    List<Notification> findByNotificationMethod(NotificationMethod method);
}
