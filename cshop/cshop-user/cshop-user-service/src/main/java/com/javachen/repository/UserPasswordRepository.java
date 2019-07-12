package com.javachen.repository;

import com.javachen.entity.User;
import com.javachen.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
    UserPassword findByUserId(Long userId);
}
