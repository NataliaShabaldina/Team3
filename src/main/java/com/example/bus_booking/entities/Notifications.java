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
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Client client;
    private NotificationType notificationType;
    private NotificationMethod notificationMethod;
    private boolean notificationIs;
}
