package com.javachen.cshop.repository;

import com.javachen.cshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteById(Long id);

    User findByPhone(String phone);

    User findByUsername(String username);

    User findByUsernameOrPhone(String username, String phone);

    User findByEmail(String email);

    @Query("select u from User u")
    Page<User> findALL(Pageable pageable);
}
