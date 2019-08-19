package com.javachen.cshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author june
 * @createTime 2019-06-16 22:52
 * @see
 * @since
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AppItem {
    public static void main(String[] args) {
        SpringApplication.run(AppItem.class, args);
    }
}
