package com.example.bus_booking.enums;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");
    private String description;
    Gender(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
