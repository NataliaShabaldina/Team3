package com.example.bus_booking.enums;

public enum ReviewRating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int rating;

    ReviewRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}