package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {
    Optional<ConfirmationCode> findByCode(String code);

    Optional<ConfirmationCode> findByClient_Id(Long clientId);

    Optional<ConfirmationCode> findByIsUsed(Boolean isUsed);
}
