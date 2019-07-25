package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.entity.User;
import com.javachen.cshop.model.form.PasswordChange;
import com.javachen.cshop.model.form.PasswordReset;
import com.javachen.cshop.service.AccountService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 修改密码
 *
 * @author june
 * @createTime 2019-06-27 10:20
 * @see
 * @since
 */
@Api("密码接口")
@Controller
public class PasswordController {
    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ApiImplicitParam(name = "passwordChange", required = true, value = "修改密码对象")
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 500, message = "服务器异常")
    })
    @PostMapping("/password")
    public RestResponse<User> processChangePassword(@Valid PasswordChange passwordChange) {
        return RestResponse.success(accountService.changePassword(passwordChange));
    }

    /**
     * 重置密码，发送邮件
     *
     * @param email
     * @return
     */
    @GetMapping(value = "/password")
    public void processForgotPassword(@RequestParam("email") String email) {
        accountService.sendForgotPasswordNotification(email, this.getResetPasswordUrl());
    }

    /**
     * 重置密码接口
     *
     * @param passwordReset
     */
    @PutMapping(value = "/password")
    public void processResetPassword(@Valid PasswordReset passwordReset) {
        accountService.resetPassword(passwordReset);
    }


    public String getResetPasswordUrl() {
        return "";
    }
}
