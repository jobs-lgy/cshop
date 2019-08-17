package com.javachen.cshop.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.javachen.cshop")
public class AppAuthJdbc {
    public static void main(String[] args) {
        SpringApplication.run(AppAuthJdbc.class, args);
    }
}
