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
