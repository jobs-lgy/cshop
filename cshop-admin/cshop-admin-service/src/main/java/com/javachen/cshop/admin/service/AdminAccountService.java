package com.javachen.cshop.admin.service;


import com.javachen.cshop.admin.entity.User;
import com.javachen.cshop.admin.model.form.PasswordChange;
import com.javachen.cshop.admin.model.form.PasswordReset;
import com.javachen.cshop.admin.model.form.UserLogin;
import com.javachen.cshop.admin.model.form.UserRegister;

public interface AdminAccountService {
    String sendVerifyCode(String phone);

    User register(UserRegister userRegister);

    User login(UserLogin userLogin);

    User logout(PasswordChange passwordChange);

    User changePassword(PasswordChange passwordChange);

    void sendForgotUsernameNotification(String email);

    void sendResetPasswordNotification(String username);

    User resetPassword(PasswordReset passwordReset);
}
