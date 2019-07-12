package com.javachen.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AppSms {
    public static void main(String[] args) {
        SpringApplication.run(AppSms.class,args);
    }
}
