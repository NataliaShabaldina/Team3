package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT n FROM Review n WHERE client = :clientId")
    List<Review> findByClientIdAndReview(@Param("clientId") Long clientId);

    @Query("SELECT n FROM Review n WHERE bus = :busId")
    List<Review> findByBusIdAndReview(@Param("busId") Long busId);
}
