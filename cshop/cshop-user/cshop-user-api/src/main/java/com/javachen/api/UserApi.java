package com.javachen.api;

import com.javachen.common.response.CommonResponse;
import com.javachen.form.LoginForm;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public interface UserApi {
    @PostMapping(value = "/login")
    public CommonResponse login(LoginForm loginForm) ;
}
