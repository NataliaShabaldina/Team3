package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    //поиск клиента по email
    Client findByEmail(String email);

    //поиск клиента по номеру телефона
    Client findByPhoneNumber(String phoneNumber);

    //проверить, подтвержден ли клиент
    boolean existsByIdAndIsVerifiedTrue(Long clientId);

}
