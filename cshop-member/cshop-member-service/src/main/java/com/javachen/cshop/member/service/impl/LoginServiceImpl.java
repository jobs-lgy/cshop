package com.javachen.cshop.member.service.impl;

import com.javachen.cshop.member.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public String wechatLogin(Map<String, Object> userInfoMap, String openId) {
        //获取微信用户信息入库——用户昵称
        String nickName = userInfoMap.get("nickname").toString();
        //获取微信用户信息入库——用户性别
        String sex = userInfoMap.get("sex").toString();
        //获取微信用户信息入库——用户头像
        String headimgurl = userInfoMap.get("headimgurl").toString();


        return null;
    }
}
