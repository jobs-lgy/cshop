package com.javachen.cshop.api;

import com.javachen.cshop.entity.User;
import com.javachen.cshop.model.form.PasswordChange;
import com.javachen.cshop.model.form.PasswordReset;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.model.form.UserRegister;
import com.javachen.cshop.model.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    @PostMapping(value = "/login")
    User processLogin(UserLogin userLogin);

    @GetMapping(value = "/login/forgotPassword")
    void processForgotPassword(@RequestParam("email") String email);

    @PostMapping(value = "/login/resetPassword")
    void processResetPassword(PasswordReset passwordReset);

    @PostMapping("/account/password")
    User processChangePassword(PasswordChange passwordChange);

    @GetMapping("/register/code")
    String senVerifyCode(@RequestParam("phone") String phone);

    @PostMapping("/register")
    UserVo register(UserRegister userRegister);

}
