package com.javachen.cshop.config;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

/**
 * @author: 98050
 * @create: 2018-10-27
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "cshop.wxpay")
public class PayConfig implements WXPayConfig {
    /**
     * 公众账号ID
     */
    private String appID;

    /**
     * 商户号
     */
    private String mchID;

    /**
     * 生成签名的密钥
     */
    private String key;

    /**
     * 连接超时时间
     */
    private int httpConnectTimeoutMs;

    /**
     * 读取超时时间
     */
    private int httpReadTimeoutMs;


    @Override
    public InputStream getCertStream() {
        return null;
    }

}
