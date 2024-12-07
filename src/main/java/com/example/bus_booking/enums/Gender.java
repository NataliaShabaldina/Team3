package com.example.bus_booking.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Мужской"),
    OTHER("Другое"),
    FEMALE("Женский");

    private final String description;
    Gender(String description) {
        this.description = description;
    }
}
