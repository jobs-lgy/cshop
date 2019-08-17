package com.javachen.cshop.weixin.service;

public interface VerificationCodeService {
    boolean verify(String phone, String code);
}
