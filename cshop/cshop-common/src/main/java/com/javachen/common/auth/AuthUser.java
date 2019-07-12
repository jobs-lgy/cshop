package com.javachen.common.auth;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class AuthUser {

    private Long id;

    private String username;

    public AuthUser() {
    }

    public AuthUser(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}