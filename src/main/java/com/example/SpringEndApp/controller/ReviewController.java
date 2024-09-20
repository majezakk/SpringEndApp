package com.example.SpringEndApp.controller;

import com.example.SpringEndApp.model.ReviewModel;
import com.example.SpringEndApp.service.ReviewService;
import com.example.SpringEndApp.service.DishService;
import com.example.SpringEndApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final DishService dishService;
    private final CustomerService customerService;

    @Autowired
    public ReviewController(ReviewService reviewService, DishService dishService, CustomerService customerService) {
        this.reviewService = reviewService;
        this.dishService = dishService;
        this.customerService = customerService;
    }

    @GetMapping
    public String listReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "review/list";
    }

    @GetMapping("/new")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new ReviewModel());
        model.addAttribute("dishes", dishService.getAllDishes());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "review/form";
    }

    @PostMapping
    public String saveReview(@Valid @ModelAttribute("review") ReviewModel review, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("dishes", dishService.getAllDishes());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "review/form";
        }
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String editReviewForm(@PathVariable Long id, Model model) {
        ReviewModel review = reviewService.getReviewById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review Id:" + id));
        model.addAttribute("review", review);
        model.addAttribute("dishes", dishService.getAllDishes());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "review/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}