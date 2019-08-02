package com.javachen.cshop.auth.controller;

import com.google.common.collect.Maps;
import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

/**
 * 登录管理
 */
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/user/login")
    public RestResponse<Void> processLogin(@Valid UserLogin userLogin) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("token", authService.login(userLogin));
        return RestResponse.success(result);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/user/logout")
    public RestResponse<Void> processLogout(HttpServletRequest request) {
        String token = request.getParameter("access_token");
        authService.logout(null,token);

        return RestResponse.success();
    }

    @GetMapping(value = "/user/info")
    public RestResponse<Principal> user(Principal user) {
        return RestResponse.success(user);
    }
}
