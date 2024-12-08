package com.example.bus_booking.entities;

import com.example.bus_booking.enums.OrderStatus;
import com.example.bus_booking.enums.PaymentMethod;
import com.example.bus_booking.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    private Bus bus;

    @Column(nullable = false)
    private LocalDateTime startTime = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime endTime;

    private String startPoint;
    private String endPoint;

    private int numberOfPeople;
    private String commentOfClient;
    private int price;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

}
