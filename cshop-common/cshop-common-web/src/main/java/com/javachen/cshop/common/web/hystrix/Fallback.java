package com.javachen.cshop.common.web.hystrix;

/**
 * 通用的熔断方法
 */
public class Fallback {
    public static String fail() {
        return null;
    }
}
