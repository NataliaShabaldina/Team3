package com.example.bus_booking.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCallBack {
    private Long orderId;
    private String paymentId;
    private String signature;
    private boolean success;

}
