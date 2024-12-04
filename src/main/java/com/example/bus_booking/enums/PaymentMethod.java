package com.example.bus_booking.enums;

public enum PaymentMethod {

    CASH("Наличными"),
    CARD("Банковской картой"),
    WALLET("Он-лайн кошельком");
    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

