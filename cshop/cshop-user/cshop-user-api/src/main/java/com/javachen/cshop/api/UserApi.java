package com.javachen.cshop.api;

import com.javachen.cshop.common.response.CommonResponse;
import com.javachen.cshop.entity.User;
import com.javachen.cshop.model.form.PasswordChange;
import com.javachen.cshop.model.form.PasswordReset;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.model.form.UserRegister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    @PostMapping(value = "/login")
    CommonResponse processLogin(UserLogin userLogin);

    @GetMapping(value = "/login/forgotPassword")
    CommonResponse processForgotPassword(@RequestParam("email") String email);

    @PostMapping(value = "/login/resetPassword")
    CommonResponse processResetPassword(PasswordReset passwordReset);

    @PostMapping("/account/password")
    CommonResponse processChangePassword(PasswordChange passwordChange);

    @GetMapping("/register/code")
    CommonResponse senVerifyCode(@RequestParam("phone") String phone);

    @PostMapping("/register")
    CommonResponse<User> register(UserRegister userRegister);

}
