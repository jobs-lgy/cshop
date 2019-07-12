package com.javachen.service;

import com.javachen.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findByPhone(String username);

    User findById(Long id);

    void register(User user, String password, String passwordConfirm,String code);

    void sendVerifyCode(String phone);

    User save(User user);

    Page<User> findAll(Pageable page);
}
