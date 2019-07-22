package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.CommonResponse;
import com.javachen.cshop.model.form.PasswordChange;
import com.javachen.cshop.service.AccountService;
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
@Controller
public class ChangePasswordController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/account/password")
    public CommonResponse processChangePassword(@Valid PasswordChange passwordChange) {
        return CommonResponse.success(accountService.changePassword(passwordChange));
    }
}
