package com.javachen.cshop.controller;

import com.javachen.cshop.entity.User;
import com.javachen.cshop.model.form.PasswordReset;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public User processLogin(@Valid UserLogin userLogin) {
        return accountService.login(userLogin);
    }

    /**
     * 重置密码，发送邮件
     *
     * @param email
     * @return
     */
    @PostMapping(value = "/login/forgotPassword")
    public void processForgotPassword(@RequestParam("email") String email) {
        accountService.sendForgotPasswordNotification(email, this.getResetPasswordUrl());
    }

    @PostMapping(value = "/login/resetPassword")
    public void processResetPassword(PasswordReset passwordReset) {
        accountService.resetPassword(passwordReset);
    }


    public String getResetPasswordUrl() {
        return "";
    }
}
