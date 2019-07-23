package com.javachen.cshop.controller;

import com.javachen.cshop.entity.User;
import com.javachen.cshop.model.form.PasswordChange;
import com.javachen.cshop.service.AccountService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * 修改密码
 *
 * @author june
 * @createTime 2019-06-27 10:20
 * @see
 * @since
 */
@Api("修改密码接口")
@Controller
public class ChangePasswordController {
    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ApiImplicitParam(name = "passwordChange", required = true, value = "修改密码对象")
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 500, message = "服务器异常")
    })
    @PostMapping("/account/password")
    public User processChangePassword(@Valid PasswordChange passwordChange) {
        return accountService.changePassword(passwordChange);
    }
}
