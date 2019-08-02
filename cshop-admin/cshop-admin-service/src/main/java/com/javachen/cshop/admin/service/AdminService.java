package com.javachen.cshop.admin.service;


import com.javachen.cshop.admin.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> findAll();

    Admin findById(Long id);

    Admin findByUsername(String username);

    Admin findByEmail(String email);

    Admin save(Admin user);

    void delete(Long id);
}
