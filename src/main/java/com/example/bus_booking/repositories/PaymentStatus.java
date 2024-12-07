package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatus extends JpaRepository<PaymentMethod, Long> {

}
