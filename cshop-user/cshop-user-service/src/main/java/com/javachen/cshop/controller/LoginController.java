package com.javachen.cshop.controller;

import com.javachen.cshop.entity.User;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags="登陆接口")
@RestController
public class LoginController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public User processLogin(@Valid UserLogin userLogin) {
        return accountService.login(userLogin);
    }

    @PostMapping(value = "/logout")
    public User processLogout(@Valid UserLogin userLogin) {
        return accountService.login(userLogin);
    }

}
