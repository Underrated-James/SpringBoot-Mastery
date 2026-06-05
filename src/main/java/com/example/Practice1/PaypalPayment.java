package com.example.Practice1;

import org.springframework.stereotype.Service;

@Service
public class PaypalPayment implements PaymentService{

    @Override
    public String getpaymentType() {
        return "PAYPAL";
    }

    @Override
    public void processPayment(double amount) {
        System.out.print("Paypal Payment for " + amount + " is Done!");
    }
}
