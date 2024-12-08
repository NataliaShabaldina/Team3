package com.example.bus_booking.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CONFIRMED("Подтвержден"),
    COMPLETED("Завершен"),
    CANCELLED("Отменен");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

}
