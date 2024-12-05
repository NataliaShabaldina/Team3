package com.example.bus_booking.repositories;

import ch.qos.logback.core.net.server.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    //поиск клиента по еmail
    Client findByEmail(String email);

    //поиск клиента по номеру телефона
    Client findByPhoneNumber(String phoneNumber);

    //проверить, подтвержден ли клиент
    @Query("select c From users c where c.id= :clientId AND  c.isVerified = true")
    boolean isVerified(@Param("clientId") Long clientId);
}
