package com.javachen.controller;

import com.javachen.common.response.CommonResponse;
import com.javachen.model.form.PasswordReset;
import com.javachen.model.form.UserLogin;
import com.javachen.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private AccountService accountService;

    protected static String resetPasswordView = "login/resetPassword";


    @PostMapping(value = "/login")
    public CommonResponse processLogin(@Valid UserLogin userLogin) {
        return CommonResponse.success(accountService.login(userLogin));
    }

    /**
     * 重置密码，发送邮件
     *
     * @param email
     * @param request
     * @return
     */
    @PostMapping(value = "/login/forgotPassword")
    public CommonResponse processForgotPassword(@RequestParam("email") String email,HttpServletRequest request){
        accountService.sendForgotPasswordNotification(email,this.getResetPasswordUrl(request));
        return CommonResponse.success();
    }

    @PostMapping(value = "/login/resetPassword")
    public CommonResponse processResetPassword(@PathVariable PasswordReset passwordReset){
        accountService.resetPassword(passwordReset);
        return CommonResponse.success();
    }

    public String getResetPasswordPort(HttpServletRequest request, String scheme) {
        if ("http".equalsIgnoreCase(scheme) && request.getServerPort() != 80) {
            return ":" + request.getServerPort();
        } else {
            return "https".equalsIgnoreCase(scheme) && request.getServerPort() != 443 ? ":" + request.getServerPort() : "";
        }
    }

    public String getResetPasswordUrl(HttpServletRequest request) {
        //FIXME 这应该是一个前端的重置密码页面，通过配置文件来配置？
        String url = request.getScheme() + "://" + request.getServerName() + this.getResetPasswordPort(request, request.getScheme());
        if (request.getContextPath() != null && !"".equals(request.getContextPath())) {
            url = url + request.getContextPath() + resetPasswordView;
        } else {
            url = url + resetPasswordView;
        }

        return url;
    }
}
