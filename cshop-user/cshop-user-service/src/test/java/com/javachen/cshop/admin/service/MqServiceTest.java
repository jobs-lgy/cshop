package com.javachen.cshop.admin.service;

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
public class MqServiceTest {
    @Autowired
    private MqService mqService;

    @Test
    public void testRegister() {
        mqService.sendSms("123","fff");
    }
}
