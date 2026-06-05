package com.example.Practice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final List<PaymentService> paymentService;
    int x = 10;
    String name = "Didyeey";

    @Autowired
    public OrderService(List<PaymentService> paymentService){
        this.paymentService = paymentService;
    }

    public void placeOrder(String paymentType) {

        PaymentService selected = paymentService.stream()
                .filter(p -> p.getpaymentType().equalsIgnoreCase(paymentType))
                .findFirst()
                .orElseThrow();

        selected.processPayment(10);
    }
}
