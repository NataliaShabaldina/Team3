package com.example.bus_booking.services;

import com.example.bus_booking.entities.Review;
import com.example.bus_booking.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByClientId(Long clientId) {
        return reviewRepository.findByClientId(clientId);
    }

    public List<Review> getReviewsByBusId(Long busId) {
        return reviewRepository.findByBusId(busId);
    }

    public Review updateAnswer(Long reviewId, String answer) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("Отзыв с ID " + reviewId + " не найден"));
        review.setAnswer(answer);
        return reviewRepository.save(review);
    }


    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
