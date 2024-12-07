package com.example.bus_booking.enums;

import lombok.Getter;


@Getter
public enum PaymentMethod {

    CASH("Наличными"),
    CARD("Банковской картой"),
    WALLET("Он-лайн кошельком");
    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

}

