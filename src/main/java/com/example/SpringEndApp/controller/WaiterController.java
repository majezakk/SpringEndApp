package com.example.SpringEndApp.controller;

import com.example.SpringEndApp.model.WaiterModel;
import com.example.SpringEndApp.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/waiters")
public class WaiterController {
    private final WaiterService waiterService;

    @Autowired
    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @GetMapping
    public String listWaiters(Model model) {
        model.addAttribute("waiters", waiterService.getAllWaiters());
        return "waiter/list";
    }

    @GetMapping("/new")
    public String newWaiterForm(Model model) {
        model.addAttribute("waiter", new WaiterModel());
        return "waiter/form";
    }

    @PostMapping
    public String saveWaiter(@Valid @ModelAttribute("waiter") WaiterModel waiter, BindingResult result) {
        if (result.hasErrors()) {
            return "waiter/form";
        }
        waiterService.saveWaiter(waiter);
        return "redirect:/waiters";
    }

    @GetMapping("/edit/{id}")
    public String editWaiterForm(@PathVariable Long id, Model model) {
        WaiterModel waiter = waiterService.getWaiterById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid waiter Id:" + id));
        model.addAttribute("waiter", waiter);
        return "waiter/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteWaiter(@PathVariable Long id) {
        waiterService.deleteWaiter(id);
        return "redirect:/waiters";
    }
}