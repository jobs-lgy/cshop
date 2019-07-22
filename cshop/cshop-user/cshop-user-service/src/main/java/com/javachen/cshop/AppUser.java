package com.javachen.cshop;

import com.javachen.cshop.processor.EmailOutputProcessor;
import com.javachen.cshop.processor.SmsOutputProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableBinding({SmsOutputProcessor.class, EmailOutputProcessor.class})
@EnableAsync
@EnableCaching
@SpringBootApplication
//@EnableDiscoveryClient
public class AppUser  {
    public static void main(String[] args) {
        SpringApplication.run(AppUser.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
