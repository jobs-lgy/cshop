package com.javachen.cshop.weixin.service;

import java.util.Map;

public interface LoginService {
    String login(Map<String, Object> userInfoMap, String openId);
}
