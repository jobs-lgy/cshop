package com.javachen.cshop.sms;

import com.javachen.cshop.sms.processor.SmsInputProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableDiscoveryClient
@EnableBinding(value = {SmsInputProcessor.class})
@SpringBootApplication
public class AppSms {
    public static void main(String[] args) {
        SpringApplication.run(AppSms.class, args);
    }
}
