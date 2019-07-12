package com.javachen.service;

import com.javachen.entity.User;

/**
 * @author june
 * @createTime 2019-06-27 10:41
 * @see
 * @since
 */
public interface LoginService {
    public User loginByUsername(String username, String clearTextPassword);

    public User loginByPhone(String phone, String clearTextPassword);

    public void logoutUser();
}
