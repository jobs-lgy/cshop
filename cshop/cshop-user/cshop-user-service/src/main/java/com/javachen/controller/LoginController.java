package com.javachen.controller;

import com.javachen.common.response.CommonResponse;
import com.javachen.entity.User;
import com.javachen.form.LoginForm;
import com.javachen.form.ResetPasswordForm;
import com.javachen.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("account")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Value("${cshop.login.withPhone:true}")
    protected boolean usePhoneForLogin;

    @PostMapping(value = "/login")
    public CommonResponse login(@Valid LoginForm loginForm) {
        User user=null;
        if(usePhoneForLogin){
            user = loginService.loginByPhone(loginForm.getUsername(),loginForm.getPassword());
        }else{
            user = loginService.loginByUsername(loginForm.getUsername(),loginForm.getPassword());
        }
        return CommonResponse.success(user);
    }

    @PostMapping(value = "/login/forgotPassword")
    public CommonResponse processForgotPassword(@RequestParam("email") String email){
        return CommonResponse.success();
    }

    @PostMapping(value = "/login/resetPassword")
    public CommonResponse processResetPassword(@ModelAttribute("resetPasswordForm") ResetPasswordForm resetPasswordForm){
        return CommonResponse.success();
    }
}
