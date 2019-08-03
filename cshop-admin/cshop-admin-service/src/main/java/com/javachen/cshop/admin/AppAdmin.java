package com.javachen.cshop.admin;

import com.javachen.cshop.admin.processor.EmailOutputProcessor;
import com.javachen.cshop.admin.processor.SmsOutputProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableBinding({SmsOutputProcessor.class, EmailOutputProcessor.class})
@EnableAsync
@EnableCaching
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.javachen.cshop")
@EnableDiscoveryClient
public class AppAdmin {
    public static void main(String[] args) {
        SpringApplication.run(AppAdmin.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
