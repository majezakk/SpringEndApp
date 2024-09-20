package com.example.SpringEndApp.service;

import com.example.SpringEndApp.model.ReviewModel;
import com.example.SpringEndApp.repository.ReviewModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewModelRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewModelRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewModel> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<ReviewModel> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public ReviewModel saveReview(ReviewModel review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}