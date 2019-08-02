package com.javachen.cshop.email;

import com.javachen.cshop.email.processor.EmailInputProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author june
 * @createTime 2019-07-19 23:44
 * @see
 * @since
 */
@EnableBinding({EmailInputProcessor.class})
@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
public class AppEmail {
    public static void main(String[] args) {
        SpringApplication.run(AppEmail.class, args);
    }
}
