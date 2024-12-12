package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Payment;
import com.example.bus_booking.enums.PaymentStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentStatusRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByClientId(Long client);
    List<Payment> findByPaymentStatus(PaymentStatusEnum paymentStatusEnum);


}
