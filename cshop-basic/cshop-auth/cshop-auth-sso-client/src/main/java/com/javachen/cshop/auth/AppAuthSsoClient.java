package com.javachen.cshop.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableOAuth2Sso
@SpringBootApplication
public class AppAuthSsoClient {
    public static void main(String[] args) {
        SpringApplication.run(AppAuthSsoClient.class, args);
    }

    @RequestMapping("/user")
    public Authentication getUser(Authentication user) {
        return user;
    }
}
