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

import com.javachen.cshop.admin.entity.Permission;
import com.javachen.cshop.admin.repository.PermissionRepository;
import com.javachen.cshop.admin.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    protected PermissionRepository permissionRepository;

    @Override
    @Transactional
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Permission save(Permission permission) {
        permission = permissionRepository.save(permission);
        return permission;
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

}
