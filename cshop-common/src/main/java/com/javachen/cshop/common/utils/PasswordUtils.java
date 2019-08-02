package com.javachen.cshop.common.utils;


import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtils {
    private static final Random RANDOM = new SecureRandom();
    private static final String CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
    
    public static String generateSecurePassword(int requiredLength) {
        int start = 0;
        int end = CHARS.length();
        boolean letters = true;
        boolean numbers = true;
        String password = RandomStringUtils.random(requiredLength, start, end, letters, numbers, CHARS.toCharArray(), RANDOM);
        return password;
    }
}
