package com.example.bus_booking.entities;

import com.example.bus_booking.enums.OrderStatus;
import com.example.bus_booking.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Bus bus;

    private String numberOfPeople;
    private String startingPoint;
    private String endPoint;
    private int rentalCost;
    private String commentOfClient;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private LocalDateTime timeOrderCreation = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime endDate;

}
