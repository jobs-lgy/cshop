package com.javachen.cshop.service.impl;

import com.javachen.cshop.common.exception.NotFoundException;
import com.javachen.cshop.entity.User;
import com.javachen.cshop.repository.UserRepository;
import com.javachen.cshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable("byPhone")
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("id=1"));
    }

    @Override
    @CachePut(value = "byPhone", key = "#p0.phone")
    @Transactional
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
