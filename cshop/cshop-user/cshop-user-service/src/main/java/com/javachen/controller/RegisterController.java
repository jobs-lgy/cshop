package com.javachen.controller;

import com.javachen.common.response.CommonResponse;
import com.javachen.email.service.EmailService;
import com.javachen.entity.User;
import com.javachen.model.form.UserRegister;
import com.javachen.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private AccountService accountService;

    @Autowired
    protected EmailService emailService;

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    @GetMapping("/code")
    public CommonResponse senVerifyCode(@RequestParam("phone") String phone){
        this.accountService.sendVerifyCode(phone);
        return CommonResponse.success();
    }

    @PostMapping
    public CommonResponse<User> register(@Valid UserRegister userRegister)  {
        return CommonResponse.success(accountService.register(userRegister));
    }
}
