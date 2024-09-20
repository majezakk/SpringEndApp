package com.example.SpringEndApp.service;

import com.example.SpringEndApp.model.WaiterModel;
import com.example.SpringEndApp.repository.WaiterModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    private final WaiterModelRepository waiterRepository;

    @Autowired
    public WaiterService(WaiterModelRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    public List<WaiterModel> getAllWaiters() {
        return waiterRepository.findAll();
    }

    public Optional<WaiterModel> getWaiterById(Long id) {
        return waiterRepository.findById(id);
    }

    public WaiterModel saveWaiter(WaiterModel waiter) {
        return waiterRepository.save(waiter);
    }

    public void deleteWaiter(Long id) {
        waiterRepository.deleteById(id);
    }
}