package com.example.bus_booking.entities;

import com.example.bus_booking.enums.NotificationMethod;
import com.example.bus_booking.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    @Enumerated(EnumType.STRING)
    private NotificationMethod notificationMethod;
    private boolean notificationIs;
}
