package com.javachen.service;

import com.javachen.common.auth.AuthUser;
import com.javachen.model.form.UserLogin;

public interface AuthService {
    String authentication(UserLogin userLogin);

    AuthUser verifyToken(String token);
}
