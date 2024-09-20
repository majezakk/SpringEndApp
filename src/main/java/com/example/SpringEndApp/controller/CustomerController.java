package com.example.SpringEndApp.controller;

import com.example.SpringEndApp.model.CustomerModel;
import com.example.SpringEndApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer/list";
    }

    @GetMapping("/new")
    public String newCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerModel());
        return "customer/form";
    }

    @PostMapping
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerModel customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customer/form";
        }
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        CustomerModel customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);
        return "customer/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}