package com.javachen.cshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author june
 * @createTime 2019-07-25 19:29
 * @see
 * @since
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AppGateway {
    public static void main(String[] args) {
        SpringApplication.run(AppGateway.class, args);
    }
}
