package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.client.id= :clientId")
    List<Review> findByClientId(@Param("clientId") Long clientId);

    @Query("SELECT r FROM Review r WHERE r.bus.id = :busId")
    List<Review> findByBusId(@Param("busId") Long busId);
}
