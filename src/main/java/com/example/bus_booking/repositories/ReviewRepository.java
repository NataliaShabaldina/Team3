package com.example.bus_booking.repositories;

import com.example.bus_booking.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT n FROM Review n WHERE n.client.id = :clientId AND n.review = :review")
    List<Review> findByClientIdAndReview(@Param("clientId") Long clientId, @Param("review") Review review);

    @Query("SELECT n FROM Review n WHERE n.bus.id = :busId AND n.review = :review")
    List<Review> findByBusIdAndReview(@Param("busId") Long busId, @Param("review") Review review);
}
