package com.example.SpringEndApp.controller;

import com.example.SpringEndApp.model.DishModel;
import com.example.SpringEndApp.service.DishService;
import com.example.SpringEndApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;
    private final CategoryService categoryService;

    @Autowired
    public DishController(DishService dishService, CategoryService categoryService) {
        this.dishService = dishService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listDishes(Model model) {
        model.addAttribute("dishes", dishService.getAllDishes());
        return "dish/list";
    }

    @GetMapping("/new")
    public String newDishForm(Model model) {
        model.addAttribute("dish", new DishModel());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "dish/form";
    }

    @PostMapping
    public String saveDish(@Valid @ModelAttribute("dish") DishModel dish, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "dish/form";
        }
        dishService.saveDish(dish);
        return "redirect:/dishes";
    }

    @GetMapping("/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model) {
        DishModel dish = dishService.getDishById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid dish Id:" + id));
        model.addAttribute("dish", dish);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "dish/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/dishes";
    }
}