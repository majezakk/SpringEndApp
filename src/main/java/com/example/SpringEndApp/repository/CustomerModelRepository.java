package com.example.SpringEndApp.repository;

import com.example.SpringEndApp.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerModelRepository extends JpaRepository<CustomerModel, Long> {
}