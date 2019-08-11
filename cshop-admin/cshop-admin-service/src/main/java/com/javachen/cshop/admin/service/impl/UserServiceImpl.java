package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.admin.entity.User;
import com.javachen.cshop.admin.repository.UserRepository;
import com.javachen.cshop.admin.service.MqService;
import com.javachen.cshop.admin.service.UserService;
import com.javachen.cshop.common.exception.BaseException;
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
public class UserServiceImpl implements UserService {
    private static final String REGISTER_KEY_PREFIX = "cshop:register:";

    private static final int FULL_PASSWORD_LENGTH = 16;

    @Resource
    protected UserRepository userRepository;

    @Resource
    protected PasswordEncoder passwordEncoderBean;

    @Autowired
    private MqService mqService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User save(User user) {
        boolean encodePasswordNeeded = false;

        // If no password is set, default to a secure password.
        if (user.getPassword() == null) {
            encodePasswordNeeded = true;
            user.setPassword(generateSecurePassword());
        }

        if (encodePasswordNeeded) {
            user.setPassword(encodePassword(user.getPassword()));
        }

        User returnUser = userRepository.save(user);
        return returnUser;
    }

    protected String generateSecurePassword() {
        return PasswordUtils.generateSecurePassword(FULL_PASSWORD_LENGTH);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    protected void checkUser(User user) {
        if (user == null) {
            throw new BaseException(ErrorCode.USER_NOT_EXIST);
        } else if (StringUtils.isBlank(user.getEmail())) {
            throw new BaseException(ErrorCode.USER_NOT_EXIST);
        } else if (!user.isEnabled()) {
            throw new BaseException(ErrorCode.USER_NOT_ACTIVE);
        }
    }

    protected boolean isPasswordValid(String encodedPassword, String rawPassword) {
        return passwordEncoderBean.matches(rawPassword, encodedPassword);
    }

    protected String encodePassword(String rawPassword) {
        return passwordEncoderBean.encode(rawPassword);
    }
}
