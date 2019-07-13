package com.javachen.cshop.service.impl;

import com.javachen.cshop.common.auth.AuthFailedException;
import com.javachen.cshop.common.auth.AuthUser;
import com.javachen.cshop.common.auth.JwtHelper;
import com.javachen.cshop.common.exception.BusinessException;
import com.javachen.cshop.common.exception.ErrorCode;
import com.javachen.cshop.common.response.CommonResponse;
import com.javachen.cshop.feign.UserClient;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.model.vo.UserVo;
import com.javachen.cshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public String authentication(UserLogin userLogin) {
        CommonResponse<UserVo> commonResponse = userClient.processLogin(userLogin);

        //登陆失败，抛出异常信息
        if (commonResponse.getStatus().equals("success")) {
            throw new BusinessException(ErrorCode.AUTHORIZED_FAIL, commonResponse.getData().toString());
        }
        UserVo user = commonResponse.getData();
        return jwtHelper.generateToken(new AuthUser(user.getId(), user.getUsername()));
    }

    @Override
    public AuthUser verifyToken(String token) {
        //1.从token中解析token信息
        AuthUser userInfo = jwtHelper.getAuthUserFromToken(token);
        if (userInfo == null) {
            throw new AuthFailedException();
        }
        //2.解析成功要重新刷新token
        token = jwtHelper.generateToken(userInfo);

        return userInfo;
    }
}
