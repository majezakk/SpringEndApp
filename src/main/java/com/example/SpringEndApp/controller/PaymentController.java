package com.example.SpringEndApp.controller;

import com.example.SpringEndApp.model.PaymentModel;
import com.example.SpringEndApp.service.PaymentService;
import com.example.SpringEndApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;

    @Autowired
    public PaymentController(PaymentService paymentService, OrderService orderService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @GetMapping
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payment/list";
    }

    @GetMapping("/new")
    public String newPaymentForm(Model model) {
        model.addAttribute("payment", new PaymentModel());
        model.addAttribute("orders", orderService.getAllOrders());
        return "payment/form";
    }

    @PostMapping
    public String savePayment(@Valid @ModelAttribute("payment") PaymentModel payment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orders", orderService.getAllOrders());
            return "payment/form";
        }
        paymentService.savePayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")
    public String editPaymentForm(@PathVariable Long id, Model model) {
        PaymentModel payment = paymentService.getPaymentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid payment Id:" + id));
        model.addAttribute("payment", payment);
        model.addAttribute("orders", orderService.getAllOrders());
        return "payment/form";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "redirect:/payments";
    }
}