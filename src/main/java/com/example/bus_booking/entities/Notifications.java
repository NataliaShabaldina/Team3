package com.example.bus_booking.entities;

import com.example.bus_booking.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notifications {
    int id;
    int userId;
    NotificationType notificationType;
    boolean notificationIs;
}
