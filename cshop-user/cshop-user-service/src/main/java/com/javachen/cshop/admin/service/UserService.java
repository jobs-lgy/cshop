package com.javachen.cshop.admin.service;


import com.javachen.cshop.admin.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    void delete(Long id);
}
