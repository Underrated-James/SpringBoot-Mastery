package com.example.Practice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class Practice1Application {

    public static void main(String[] args) {
        // 1. Capture the running application context
        ApplicationContext context = SpringApplication.run(Practice1Application.class, args);

        // 2. Extract the automated DataSource bean that Spring Boot created
        DataSource dataSource = context.getBean(DataSource.class);

        // 3. Try to establish a raw connection to test the link
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("====================================================");
            System.out.println("🌟 DATABASE CONNECTION SUCCESSFUL! 🌟");
            System.out.println("Connected to: " + connection.getMetaData().getURL());
            System.out.println("Driver Name:  " + connection.getMetaData().getDriverName());
            System.out.println("====================================================");
        } catch (Exception e) {
            System.err.println("====================================================");
            System.err.println("❌ DATABASE CONNECTION FAILED! ❌");
            System.err.println("Error details: " + e.getMessage());
            System.err.println("====================================================");
        }
    }
}