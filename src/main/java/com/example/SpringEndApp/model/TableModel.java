package com.example.SpringEndApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
@Entity
@Table(name = "tables")
public class TableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Table number is required")
    @Positive(message = "Table number must be positive")
    @Column(name = "number", nullable = false)
    private Integer number;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be positive")
    @Column(name = "capacity", nullable = false)
    private Integer capacity;
}