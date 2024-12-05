package com.example.bus_booking.enums;

public enum OrderStatus {
    CONFIRMED("Подтвержден"),
    COMPLETED("Завершен"),
    CANCELLED("Отменен");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
