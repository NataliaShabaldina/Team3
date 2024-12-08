package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.TransportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Long> {
    List<TransportCompany> findByName(String name);
}
