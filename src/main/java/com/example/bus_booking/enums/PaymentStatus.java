package com.example.bus_booking.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    COMPLETED("Оплачено"),
    FAILED("Оплата не прошла");
    private final String description;
    PaymentStatus(String description) {
        this.description = description;
    }
}
