package com.example.SpringEndApp.repository;

import com.example.SpringEndApp.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryModelRepository extends JpaRepository<CategoryModel, Long> {
}