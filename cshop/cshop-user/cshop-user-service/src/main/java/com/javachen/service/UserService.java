package com.javachen.service;

import com.javachen.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 用户管理的接口
 */
public interface UserService {

    User findByPhone(String username);

    User findById(Long id);

    User save(User user);

    void delete(Long id);

    Page<User> findAll(Pageable page);
}
