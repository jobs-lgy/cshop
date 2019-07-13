package com.javachen.cshop.service;

import com.javachen.cshop.common.auth.AuthUser;
import com.javachen.cshop.model.form.UserLogin;

public interface AuthService {
    String authentication(UserLogin userLogin);

    AuthUser verifyToken(String token);
}
