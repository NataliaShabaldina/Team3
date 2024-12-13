package com.example.bus_booking.entities;

import com.example.bus_booking.enums.OrderStatus;

import com.example.bus_booking.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
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
    private PaymentStatusEnum paymentStatus;

    @AssertTrue(message = "Время окончания не может быть раньше времени начала")
    public boolean isEndTimeValide() {
        return endTime.isAfter(startTime);
    }

    public Orders(Client client, Bus bus, int seatCount) {
        this.client = client;
        this.bus = bus;
        this.numberOfPeople = seatCount;
        this.startTime = LocalDateTime.now();
        this.orderStatus = OrderStatus.PENDING;
        this.paymentStatus = PaymentStatusEnum.UNPAID;
    }

}