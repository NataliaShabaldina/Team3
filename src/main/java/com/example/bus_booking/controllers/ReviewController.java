package com.example.bus_booking.controllers;


import com.example.bus_booking.entities.Review;
import com.example.bus_booking.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.createReview(review));
    }


    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Review>> getReviewsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(reviewService.getReviewsByClientId(clientId));
    }


    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<Review>> getReviewsByBus(@PathVariable Long busId) {
        return ResponseEntity.ok(reviewService.getReviewsByBusId(busId));
    }


    @PutMapping("/{reviewId}/answer")
    public ResponseEntity<Review> updateAnswer(@PathVariable Long reviewId, @RequestBody String answer) {
        return ResponseEntity.ok(reviewService.updateAnswer(reviewId, answer));
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
