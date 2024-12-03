package com.example.bus_booking.enums;

import lombok.Getter;

@Getter
public enum NotificationType {
    BOOKING_REMIND("напоминание о бронировании"),
    PAYMENT_REMIND("напоминание об оплате");

    private final String remind;

    NotificationType(String remind) {
        this.remind = remind;
    }
}
