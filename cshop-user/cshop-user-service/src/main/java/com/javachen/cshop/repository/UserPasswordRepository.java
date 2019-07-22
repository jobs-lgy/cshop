package com.javachen.cshop.repository;

import com.javachen.cshop.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
    UserPassword findByUserId(Long userId);
}
