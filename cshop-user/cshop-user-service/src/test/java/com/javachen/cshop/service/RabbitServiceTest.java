package com.javachen.cshop.service;

import com.javachen.cshop.model.form.PasswordChange;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.model.form.UserRegister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author june
 * @createTime 2019-07-13 11:32
 * @see
 * @since
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
public class RabbitServiceTest {
    @Autowired
    private RabbitService rabbitService;

    @Test
    public void testRegister() {
        rabbitService.sendSms("123","fff");
    }
}
