package com.example.bus_booking.entities;

import com.example.bus_booking.enums.OrderStatus;
import com.example.bus_booking.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numberOfPeople;
    private String startingPoint;
    private String endPoint;
    private int rentalCost;
    private String commentOfClient;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;

    private LocalDateTime begin = LocalDateTime.of(2025,01,01,12,00);
    private LocalDateTime end = LocalDateTime.of(2025,01,02,12,00);
    private LocalDateTime timeOrderCreation = LocalDateTime.now();
}
