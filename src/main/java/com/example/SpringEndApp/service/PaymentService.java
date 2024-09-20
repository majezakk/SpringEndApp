package com.example.SpringEndApp.service;

import com.example.SpringEndApp.model.PaymentModel;
import com.example.SpringEndApp.repository.PaymentModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentModelRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentModelRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentModel> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<PaymentModel> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public PaymentModel savePayment(PaymentModel payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}