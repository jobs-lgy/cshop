package com.javachen.cshop.admin.repository;

import com.javachen.cshop.admin.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Modifying
    @Query("delete from Admin where id = ?1")
    void deleteById(Long id);

    Admin findByPhone(String phone);

    Admin findByUsername(String username);

    Admin findByEmail(String email);

    @Query("select u from Admin u")
    Page<Admin> findAll(Pageable pageable);
}
