package com.javachen.cshop.controller;

import com.javachen.cshop.auth.JwtServerHelper;
import com.javachen.cshop.common.auth.AuthUser;
import com.javachen.cshop.common.auth.JwtConstants;
import com.javachen.cshop.common.exception.BusinessException;
import com.javachen.cshop.common.exception.ErrorCode;
import com.javachen.cshop.common.response.CommonResponse;
import com.javachen.cshop.common.util.CookieUtils;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtServerHelper jwtServerHelper;

    /**
     * 登录授权
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("auth")
    public CommonResponse authentication(@Valid UserLogin userLogin,
                                         HttpServletRequest request,
                                         HttpServletResponse response
    ) {
        //1.登录校验
        String token = this.authService.authentication(userLogin);
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
        //2.将token写入cookie，并指定httpOnly为true，防止通过js获取和修改
        CookieUtils.setCookie(request, response, jwtServerHelper.getCookieName(), token, jwtServerHelper.getCookieMaxAge(), true);

        return CommonResponse.success(token);
    }

    /**
     * 用户验证
     *
     * @param token
     * @return
     */
    @GetMapping("verify")
    public CommonResponse<AuthUser> verifyUser(@CookieValue(JwtConstants.COOKIE_NAME) String token, HttpServletRequest request,
                                               HttpServletResponse response) {
        AuthUser authUser = authService.verifyToken(token);
        CookieUtils.setCookie(request, response, jwtServerHelper.getCookieName(), token, jwtServerHelper.getCookieMaxAge());
        return CommonResponse.success(authUser);
    }

}
