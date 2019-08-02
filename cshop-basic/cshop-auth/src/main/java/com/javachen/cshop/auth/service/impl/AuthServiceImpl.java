package com.javachen.cshop.auth.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import com.javachen.cshop.common.utils.JsonUtils;
import com.javachen.cshop.common.utils.OkHttpClientUtil;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.model.vo.UserWithPassword;
import com.javachen.cshop.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author june
 * @createTime 2019-08-01 17:01
 * @see
 * @since
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private static final String URL_OAUTH_TOKEN = "http://localhost:7060/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource
    public TokenStore tokenStore;

    @Override
    public String login(UserLogin userLogin) {
        // 通过 HTTP 客户端请求登录接口
        Map<String, String> params = Maps.newHashMap();
        params.put("username", userLogin.getUsername());
        params.put("password", userLogin.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        String token=null;
        try {
            // 解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = JsonUtils.fromJson(jsonString, new TypeReference<Map<String, Object>>() {});
            token = String.valueOf(jsonMap.get("access_token"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void logout(UserLogin userLogin,String token) {
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);

    }
}
