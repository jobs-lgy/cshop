package com.javachen.cshop.weixin.service.impl;

import com.javachen.cshop.common.exception.ExceptionCast;
import com.javachen.cshop.common.utils.RegexUtils;
import com.javachen.cshop.member.MemberConstants;
import com.javachen.cshop.member.MemberErrorCode;
import com.javachen.cshop.weixin.service.VerificationCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean verify(String phone, String code) {
        // 1.验证码参数是否为空
        if (StringUtils.isEmpty(phone)) {
            ExceptionCast.cast(MemberErrorCode.PHONE_IS_EMPTY);
        }
        if (!RegexUtils.checkMobile(phone)) {
            ExceptionCast.cast(MemberErrorCode.PHONE_IS_INVALID);
        }
        if (StringUtils.isEmpty(code)) {
            ExceptionCast.cast(MemberErrorCode.VERIFY_CODE_IS_EMPTY);
        }
        // 2.根据手机号码查询redis返回对应的注册码
        String codeKey = MemberConstants.WEIXIN_CODE_KEY + phone;
        String redisCode = redisTemplate.opsForValue().get(codeKey);
        if (StringUtils.isEmpty(redisCode)) {
            ExceptionCast.cast(MemberErrorCode.VERIFY_CODE_IS_EXPIRED);
        }
        // 3.redis中的注册码与传递参数的weixinCode进行比对
        if (!redisCode.equals(code)) {
            ExceptionCast.cast(MemberErrorCode.VERIFY_CODE_IS_WRONG);
        }
        // 移出对应验证码
        redisTemplate.delete(codeKey);
        return true;
    }
}
