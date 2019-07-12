package com.javachen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppImageServer {
    public static void main(String[] args) {
        SpringApplication.run(AppImageServer.class, args);
    }
}