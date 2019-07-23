package com.javachen.cshop.controller;

import com.javachen.cshop.entity.User;
import com.javachen.cshop.model.form.UserRegister;
import com.javachen.cshop.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags="注册接口")
@RestController
public class RegisterController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/register/code")
    public String senVerifyCode(@RequestParam("phone") String phone) {
       return this.accountService.sendVerifyCode(phone);
    }

    @PostMapping("/register")
    public User register(@Valid UserRegister userRegister) {
        return accountService.register(userRegister);
    }
}
