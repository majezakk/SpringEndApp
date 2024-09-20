package com.example.SpringEndApp.repository;

import com.example.SpringEndApp.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewModelRepository extends JpaRepository<ReviewModel, Long> {
}