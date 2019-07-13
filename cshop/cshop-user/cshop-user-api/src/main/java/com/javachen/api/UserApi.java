package com.javachen.api;

import com.javachen.common.response.CommonResponse;
import com.javachen.model.form.UserLogin;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserApi {
    @PostMapping(value = "/login")
    public CommonResponse login(UserLogin userLogin) ;
}
