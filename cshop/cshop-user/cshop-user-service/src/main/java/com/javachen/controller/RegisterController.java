package com.javachen.controller;

import com.javachen.common.exception.BusinessException;
import com.javachen.common.response.CommonResponse;
import com.javachen.email.service.EmailService;
import com.javachen.entity.User;
import com.javachen.vo.RegistrationForm;
import com.javachen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("account")
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    protected EmailService emailService;

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    @GetMapping("/code")
    public CommonResponse senVerifyCode(@RequestParam("phone") String phone){
        this.userService.sendVerifyCode(phone);
        return CommonResponse.success();
    }

    @PostMapping("/register")
    public CommonResponse<User> register(@Valid RegistrationForm registrationForm) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.register(registrationForm.getUser(),registrationForm.getPassword(),registrationForm.getPassword(),registrationForm.getCode());
        return CommonResponse.success();
    }
}
