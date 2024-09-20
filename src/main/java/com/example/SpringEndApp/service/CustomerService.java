package com.example.SpringEndApp.service;

import com.example.SpringEndApp.model.CustomerModel;
import com.example.SpringEndApp.repository.CustomerModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerModelRepository customerRepository;

    @Autowired
    public CustomerService(CustomerModelRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<CustomerModel> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public CustomerModel saveCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}