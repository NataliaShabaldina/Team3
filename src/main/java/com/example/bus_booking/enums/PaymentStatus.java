package com.example.bus_booking.enums;

public enum PaymentStatus {
    COMPLETED("Оплачено"),
    FAILED("Оплата не прошла");
    private final String description;
    PaymentStatus(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
