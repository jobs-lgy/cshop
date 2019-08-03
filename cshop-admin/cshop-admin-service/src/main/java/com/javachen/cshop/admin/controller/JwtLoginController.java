package com.javachen.cshop.admin.controller;

import com.google.code.kaptcha.Constants;
import com.google.common.collect.Maps;
import com.javachen.cshop.admin.model.form.UserLogin;
import com.javachen.cshop.admin.security.jwt.JwtAuthenticatioToken;
import com.javachen.cshop.admin.security.util.SecurityUtils;
import com.javachen.cshop.common.domain.response.RestResponse;
import com.javachen.cshop.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author june
 * @createTime 2019-06-27 10:20
 * @see
 * @since
 */
@RestController
public class JwtLoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/user/jwt/login")
    public RestResponse<String> processLogin(@Valid @RequestBody  UserLogin userLogin, HttpServletRequest request) {
        String captcha = userLogin.getCaptcha();

        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (kaptcha == null) {
            return RestResponse.error(ErrorCode.USER_CAPTCHA_ERROR);
        }

//		if(!captcha.equals(kaptcha)){
//			return HttpResult.error("验证码不正确");
//		}
        // 系统登录认证
        JwtAuthenticatioToken jwtAuthenticatioToken = SecurityUtils.login(request, userLogin.getUsername(), userLogin.getPassword(), authenticationManager);
        Map map=Maps.newHashMap();
        map.put("token",jwtAuthenticatioToken.getToken());
        return RestResponse.success(map);
    }
}
