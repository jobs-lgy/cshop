package com.javachen.cshop.admin.service;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.HashMap;
import java.util.Map;

public class MapTest{
    public static void main(String[] args) {
        Map map=new HashMap<>();

        System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"));
    }
}