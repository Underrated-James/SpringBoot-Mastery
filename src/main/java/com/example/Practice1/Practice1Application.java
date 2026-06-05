package com.example.Practice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Practice1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Practice1Application.class, args);
        var Order = context.getBean(OrderService.class);
        Order.placeOrder("PAYPAL");

	}

}
