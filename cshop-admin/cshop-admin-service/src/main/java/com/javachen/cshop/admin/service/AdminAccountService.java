package com.javachen.cshop.admin.service;


import com.javachen.cshop.admin.entity.Admin;
import com.javachen.cshop.admin.model.form.PasswordChange;
import com.javachen.cshop.admin.model.form.PasswordReset;
import com.javachen.cshop.admin.model.form.UserLogin;
import com.javachen.cshop.admin.model.form.UserRegister;

public interface AdminAccountService {
    String sendVerifyCode(String phone);

    Admin register(UserRegister userRegister);

    Admin login(UserLogin userLogin);

    Admin logout(PasswordChange passwordChange);

    Admin changePassword(PasswordChange passwordChange);

    void sendForgotUsernameNotification(String email);

    void sendResetPasswordNotification(String username);

    Admin resetPassword(PasswordReset passwordReset);
}
