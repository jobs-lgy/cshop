package com.javachen.cshop.controller;

import com.javachen.cshop.common.response.CommonResponse;
import com.javachen.cshop.model.form.PasswordReset;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public CommonResponse processLogin(@Valid UserLogin userLogin) {
        return CommonResponse.success(accountService.login(userLogin));
    }

    /**
     * 重置密码，发送邮件
     *
     * @param email
     * @return
     */
    @GetMapping(value = "/login/forgotPassword")
    public CommonResponse processForgotPassword(@RequestParam("email") String email){
        accountService.sendForgotPasswordNotification(email,this.getResetPasswordUrl());
        return CommonResponse.success();
    }

    @PostMapping(value = "/login/resetPassword")
    public CommonResponse processResetPassword(PasswordReset passwordReset){
        accountService.resetPassword(passwordReset);
        return CommonResponse.success();
    }


    public String getResetPasswordUrl() {
        return "";
    }
}
