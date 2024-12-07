package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Notifications;
import com.example.bus_booking.enums.NotificationMethod;
import com.example.bus_booking.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Long> {

    @Query("SELECT n FROM Notifications n WHERE n.notificationIs = true")
    List<Notifications> findActiveNotifications();

    @Query("SELECT n FROM Notifications n WHERE n.client.id = :clientId AND n.notificationType = :type")
    List<Notifications> findByClientIdAndNotificationType(@Param("clientId") Long clientId, @Param("type") NotificationType type);

    List<Notifications> findByNotificationMethod(NotificationMethod method);
}
