package com.javachen.service;

import com.javachen.common.auth.AuthUser;
import com.javachen.form.LoginForm;

public interface AuthService {
    String authentication(LoginForm loginForm);

    AuthUser verifyToken(String token);
}
