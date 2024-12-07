package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT * FROM reviews WHERE client_id = :clientId")
    List<Review> findByClientIdAndReview(@Param("clientId") Long clientId);

    @Query("SELECT * FROM reviews WHERE bus_id = :busId")
    List<Review> findByBusIdAndReview(@Param("busId") Long busId);
}
