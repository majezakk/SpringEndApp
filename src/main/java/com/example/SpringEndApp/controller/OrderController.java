package com.example.SpringEndApp.controller;

import com.example.SpringEndApp.model.OrderModel;
import com.example.SpringEndApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final TableService tableService;
    private final WaiterService waiterService;

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService,
                           TableService tableService, WaiterService waiterService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.tableService = tableService;
        this.waiterService = waiterService;
    }

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/list";
    }

    @GetMapping("/new")
    public String newOrderForm(Model model) {
        model.addAttribute("order", new OrderModel());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("tables", tableService.getAllTables());
        model.addAttribute("waiters", waiterService.getAllWaiters());
        return "order/form";
    }

    @PostMapping
    public String saveOrder(@Valid @ModelAttribute("order") OrderModel order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("tables", tableService.getAllTables());
            model.addAttribute("waiters", waiterService.getAllWaiters());
            return "order/form";
        }
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable Long id, Model model) {
        OrderModel order = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("tables", tableService.getAllTables());
        model.addAttribute("waiters", waiterService.getAllWaiters());
        return "order/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}