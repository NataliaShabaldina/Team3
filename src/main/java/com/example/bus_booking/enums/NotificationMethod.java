package com.example.bus_booking.enums;

import lombok.Getter;

@Getter
public enum NotificationMethod {
    EMAIL("почта"),
    PHONE("телефон");

    private final String description;

    NotificationMethod(String description) {this.description = description;}
}
