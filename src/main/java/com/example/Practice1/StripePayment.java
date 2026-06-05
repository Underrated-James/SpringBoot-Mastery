package com.example.Practice1;

import org.springframework.stereotype.Service;

@Service
public class StripePayment implements PaymentService {

    @Override
    public String getpaymentType() {
        return "STRIPE";
    }

    @Override
    public void processPayment(double amount) {
        System.out.print("Stripe Payment for " + amount + " is done!");
    }
}
