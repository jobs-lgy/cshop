package com.javachen.form;

import com.javachen.entity.User;

import java.io.Serializable;

public class RegisterUserForm implements Serializable {
    protected static final long serialVersionUID = 1L;

    protected User user;
    protected String password;
    protected String passwordConfirm;
    protected String redirectUrl;
}
