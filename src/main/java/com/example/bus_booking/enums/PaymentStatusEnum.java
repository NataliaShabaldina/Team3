package com.example.bus_booking.enums;



public enum PaymentStatusEnum {
    PAID("Оплачено"),
    UNPAID("Не оплачено");

    private final String description;

    PaymentStatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
