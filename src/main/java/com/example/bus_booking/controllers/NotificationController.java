package com.example.bus_booking.controllers;


import com.example.bus_booking.entities.Notification;
import com.example.bus_booking.enums.NotificationMethod;
import com.example.bus_booking.enums.NotificationType;
import com.example.bus_booking.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/active")
    public ResponseEntity<List<Notification>> getActiveNotifications() {
        return ResponseEntity.ok(notificationService.getActiveNotifications());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Notification>> getNotificationsByClientAndType(@PathVariable Long clientId, @RequestParam NotificationType notificationType) {
        return ResponseEntity.ok(notificationService.getNotificationsByClientAndType(clientId, notificationType));
    }

    @GetMapping("/method")
    public ResponseEntity<List<Notification>> getNotificationsByMethod(@RequestParam NotificationMethod notificationMethod) {
        return ResponseEntity.ok(notificationService.getNotificationsByMethod(notificationMethod));
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.saveNotification(notification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
