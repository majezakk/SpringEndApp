package com.example.SpringEndApp.controller;

import com.example.SpringEndApp.model.CategoryModel;
import com.example.SpringEndApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/list";
    }

    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("category", new CategoryModel());
        return "category/form";
    }

    @PostMapping
    public String saveCategory(@Valid @ModelAttribute("category") CategoryModel category,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "category/form";
        }
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        CategoryModel category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("category", category);
        return "category/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}