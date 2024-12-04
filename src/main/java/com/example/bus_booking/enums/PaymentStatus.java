package com.example.bus_booking.enums;

public enum PaymentStatus {
    PENDING("Ожидает"),
    COMPLETED("Оплачено"),
    FAILED("Оплата не прошла"),
    REFUNDED("Возвращено");
    private final String description;
    PaymentStatus(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
