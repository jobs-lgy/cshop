package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.admin.entity.Role;
import com.javachen.cshop.admin.repository.RoleRepository;
import com.javachen.cshop.admin.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    protected RoleRepository roleRepository;

    @Override
    @Transactional
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        role = roleRepository.save(role);
        return role;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAllByUserId(Long userId) {
        return roleRepository.findAllByUserId(userId);
    }

}
