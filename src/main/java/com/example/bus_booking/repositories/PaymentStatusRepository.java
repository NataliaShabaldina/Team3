package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
    List<PaymentStatus> findByClientId(Long client);
    List<PaymentStatus> findByPaymentStatus(PaymentStatus paymentStatus);


}
