package com.example.SpringEndApp.service;

import com.example.SpringEndApp.model.DishModel;
import com.example.SpringEndApp.repository.DishModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private final DishModelRepository dishRepository;

    @Autowired
    public DishService(DishModelRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<DishModel> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<DishModel> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    public DishModel saveDish(DishModel dish) {
        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}