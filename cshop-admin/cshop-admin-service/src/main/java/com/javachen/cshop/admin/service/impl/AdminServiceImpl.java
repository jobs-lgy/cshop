/*
 * #%L
 * BroadleafCommerce Open Admin Platform
 * %%
 * Copyright (C) 2009 - 2016 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 *
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.admin.entity.Admin;
import com.javachen.cshop.admin.repository.AdminRepository;
import com.javachen.cshop.admin.service.AdminService;
import com.javachen.cshop.admin.service.MqService;
import com.javachen.cshop.common.exception.BusinessException;
import com.javachen.cshop.common.exception.ErrorCode;
import com.javachen.cshop.common.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    private static final String REGISTER_KEY_PREFIX = "cshop:register:";

    private static final int FULL_PASSWORD_LENGTH = 16;

    @Resource
    protected AdminRepository adminRepository;

    @Resource
    protected PasswordEncoder passwordEncoderBean;

    @Autowired
    private MqService mqService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Admin save(Admin user) {
        boolean encodePasswordNeeded = false;

        // If no password is set, default to a secure password.
        if (user.getPassword() == null) {
            encodePasswordNeeded = true;
            user.setPassword(generateSecurePassword());
        }

        if (encodePasswordNeeded) {
            user.setPassword(encodePassword(user.getPassword()));
        }

        Admin returnUser = adminRepository.save(user);
        return returnUser;
    }

    protected String generateSecurePassword() {
        return PasswordUtils.generateSecurePassword(FULL_PASSWORD_LENGTH);
    }


    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    protected void checkUser(Admin user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_EXIST);
        } else if (StringUtils.isBlank(user.getEmail())) {
            throw new BusinessException(ErrorCode.USER_NOT_EXIST);
        } else if (!user.isActive()) {
            throw new BusinessException(ErrorCode.USER_NOT_ACTIVE);
        }
    }

    protected boolean isPasswordValid(String encodedPassword, String rawPassword) {
        return passwordEncoderBean.matches(rawPassword, encodedPassword);
    }

    protected String encodePassword(String rawPassword) {
        return passwordEncoderBean.encode(rawPassword);
    }
}
