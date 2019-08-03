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

import com.javachen.cshop.admin.entity.Resource;
import com.javachen.cshop.admin.repository.ResourceRepository;
import com.javachen.cshop.admin.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {
    @javax.annotation.Resource
    protected ResourceRepository resourceRepository;

    @Override
    @Transactional
    public void delete(Long id) {
        resourceRepository.deleteById(id);
    }

    @Override
    public Resource findById(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Resource save(Resource resource) {
        resource = resourceRepository.save(resource);
        return resource;
    }

    @Override
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    @Override
    public List<Resource> findAllByUserId(Long userId) {
        return resourceRepository.findAllByUserId(userId);
    }

    @Override
    public List<Resource> findAllByRoleIds(List<Long> roleIds) {
        return resourceRepository.findAllByRoleIds(roleIds);
    }
}
