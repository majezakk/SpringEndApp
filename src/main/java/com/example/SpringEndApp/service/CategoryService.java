package com.example.SpringEndApp.service;

import com.example.SpringEndApp.model.CategoryModel;
import com.example.SpringEndApp.repository.CategoryModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryModelRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryModelRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryModel> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryModel saveCategory(CategoryModel category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}