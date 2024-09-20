package com.example.SpringEndApp.repository;

import com.example.SpringEndApp.model.WaiterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterModelRepository extends JpaRepository<WaiterModel, Long> {
}