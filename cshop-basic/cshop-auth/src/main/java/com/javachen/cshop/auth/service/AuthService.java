package com.javachen.cshop.auth.service;

import com.javachen.cshop.admin.model.form.UserLogin;

/**
 * @author june
 * @createTime 2019-08-01 16:58
 * @see
 * @since
 */
public interface AuthService {
    String login(UserLogin userLogin);

    void logout(UserLogin userLogin, String token);
}
