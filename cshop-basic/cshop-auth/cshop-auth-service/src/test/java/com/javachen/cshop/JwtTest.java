package com.javachen.cshop;

import com.javachen.cshop.common.auth.AuthUser;
import com.javachen.cshop.common.auth.JwtHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppAuth.class)
public class JwtTest {


//    @Test
//    public void testRsa() throws Exception {
//        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
//    }

    @Autowired
    private JwtHelper jwtServerHelper;

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = jwtServerHelper.generateToken(new AuthUser(20L, "jack"));
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU2MjEyMDQ4N30.bUmcSLY0bIhZIxk2RqWWmLbticU_pIWuN7txJ5dq-itKV3Op6WeLK7HsJERsN7QKTMcgAtYFqE1y-fmiaqe2ASL86euAZsLtVD1nogtEgLqfvWOvBZ5JFvpiqk7zafTCI_SuxmU8e3QrMlaNoQBWIQFocbBjUeYyvMzYhu1u6QM";

        // 解析token
        AuthUser user = jwtServerHelper.getAuthUserFromToken(token);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }

}