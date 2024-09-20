package com.example.SpringEndApp.repository;

import com.example.SpringEndApp.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderModelRepository extends JpaRepository<OrderModel, Long> {
}