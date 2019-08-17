package com.javachen.cshop.admin.controller;

import com.javachen.cshop.admin.entity.User;
import com.javachen.cshop.admin.model.form.PasswordChange;
import com.javachen.cshop.admin.model.form.PasswordReset;
import com.javachen.cshop.admin.model.form.UserLogin;
import com.javachen.cshop.admin.model.form.UserRegister;
import com.javachen.cshop.admin.service.UserAccountService;
import com.javachen.cshop.common.model.response.RestResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author june
 * @createTime 2019-06-27 10:20
 * @see
 * @since
 */
@RestController
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/user/register")
    public RestResponse<String> senVerifyCode(@RequestParam("phone") String phone) {
        return RestResponse.success(userAccountService.sendVerifyCode(phone));
    }

    @PostMapping("/user/register")
    public RestResponse<User> register(@Valid UserRegister userRegister) {
        return RestResponse.success(userAccountService.register(userRegister));
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ApiImplicitParam(name = "passwordChange", required = true, value = "修改密码对象")
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 500, message = "服务器异常")
    })
    @PostMapping("/user/password")
    public RestResponse<User> processChangePassword(@Valid PasswordChange passwordChange) {
        return RestResponse.success(userAccountService.changePassword(passwordChange));
    }

    /**
     * 重置密码，发送邮件
     *
     * @param email
     * @return
     */
    @GetMapping(value = "/user/password")
    public void processForgotPassword(@RequestParam("email") @NotNull String email) {
        userAccountService.sendResetPasswordNotification(email);
    }

    /**
     * 重置密码接口
     *
     * @param passwordReset
     */
    @PutMapping(value = "/user/password")
    public void processResetPassword(@Valid PasswordReset passwordReset) {
        userAccountService.resetPassword(passwordReset);
    }

    @PostMapping(value = "/user/login")
    public RestResponse<User> processLogin(@Valid UserLogin userLogin) {
        return RestResponse.success(userAccountService.login(userLogin));
    }

    @PostMapping(value = "/user/logout")
    public RestResponse<User> processLogout(HttpServletRequest request) {
        return RestResponse.success();
    }
}
