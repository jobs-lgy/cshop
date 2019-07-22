package com.javachen.cshop;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author june
 * @createTime 2019-07-18 14:15
 * @see
 * @since
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class AppMonitor {
    public static void main(String[] args) {
        SpringApplication.run(AppMonitor.class, args);
    }
}
