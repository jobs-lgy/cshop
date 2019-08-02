package com.javachen.cshop.admin.service;

import com.javachen.cshop.admin.model.form.PasswordChange;
import com.javachen.cshop.admin.model.form.UserLogin;
import com.javachen.cshop.admin.model.form.UserRegister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author june
 * @createTime 2019-07-13 11:32
 * @see
 * @since
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
public class AccountServiceTest {
    @Autowired
    private AdminAccountService accountService;

    @Test
    public void testRegister() {
        UserRegister userRegister = new UserRegister();
        userRegister.setEmail("junezchen@163.com");
        userRegister.setUsername("javachen1");
        userRegister.setPhone("17602538618");
        userRegister.setPassword("123456");
        userRegister.setPasswordConfirm("123456");
        accountService.register(userRegister);
    }

    @Test
    public void testChangePassword() {
        PasswordChange passwordChange = new PasswordChange();
        passwordChange.setUsername("junecloud@163.com");
        passwordChange.setCurrentPassword("123456");
        passwordChange.setNewPassword("654321");
        passwordChange.setNewPasswordConfirm("654321");
        accountService.changePassword(passwordChange);
    }

    @Test
    public void sendResetPasswordNotification() {
        accountService.sendResetPasswordNotification("junecloud@163.com");
    }

    @Test
    public void sendVerifyCode() {
        accountService.sendVerifyCode("17602538617");
    }

    @Test
    public void login() {
        UserLogin userLogin = new UserLogin();
        userLogin.setPassword("1");
        accountService.login(userLogin);
    }
}
