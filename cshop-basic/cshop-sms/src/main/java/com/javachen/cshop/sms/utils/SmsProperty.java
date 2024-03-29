package com.javachen.cshop.sms.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class SmsProperty {
    @Value("${cshop.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${cshop.sms.accessKeySecret}")
    private String accessKeySecret;

    @Value("${cshop.sms.signName}")
    private String signName;

    @Value("${cshop.sms.verifyCodeTemplate}")
    private String verifyCodeTemplate;
}
