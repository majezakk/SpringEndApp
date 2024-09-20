package com.example.SpringEndApp.repository;

import com.example.SpringEndApp.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentModelRepository extends JpaRepository<PaymentModel, Long> {
}