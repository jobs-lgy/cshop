package com.javachen.cshop.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppCache {
    public static void main(String[] args) {
        SpringApplication.run(AppCache.class, args);
    }
}
