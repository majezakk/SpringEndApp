package com.example.SpringEndApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "waiters")
public class WaiterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Waiter name is required")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;
}