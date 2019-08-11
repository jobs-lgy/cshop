package com.javachen.cshop.member.service;

import java.util.Map;

public interface LoginService {
    String wechatLogin(Map<String, Object> userInfoMap, String openId);
}
