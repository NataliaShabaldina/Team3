package com.example.bus_booking.enums;

public enum OrderStatus {
    PENDING("Ожидает подтверждения"),
    CONFIRMED("Подтвержден"),
    COMPLETED("Завершен"),
    CANCELLED("Отменен"),
    IN_PROGRESS("В процессе");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
