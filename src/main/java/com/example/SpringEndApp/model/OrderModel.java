package com.example.SpringEndApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private TableModel table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waiter_id")
    private WaiterModel waiter;

    @NotNull(message = "Order date is required")
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "status", length = 50)
    private String status;
}