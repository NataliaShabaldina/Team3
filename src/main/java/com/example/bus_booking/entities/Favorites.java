package com.example.bus_booking.entities;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Entity
public class Favorites {
    private List<Bus> favoriteBuses = new ArrayList<>();
}