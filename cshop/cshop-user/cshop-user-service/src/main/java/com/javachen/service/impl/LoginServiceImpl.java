package com.javachen.service.impl;

import com.javachen.common.exception.BusinessException;
import com.javachen.common.exception.ErrorCode;
import com.javachen.entity.User;
import com.javachen.entity.UserPassword;
import com.javachen.repository.UserPasswordRepository;
import com.javachen.repository.UserRepository;
import com.javachen.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author june
 * @createTime 2019-06-27 10:43
 * @see
 * @since
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loginByUsername(String username, String clearTextPassword) {
        User user=userRepository.findByUsername(username);
        return loginByUser(user,clearTextPassword);
    }

    private User loginByUser(User user, String clearTextPassword) {
        if(user==null){
            throw new BusinessException(ErrorCode.USER_NOT_EXIST);
        }
        if(!user.getActive()){
            throw new BusinessException(ErrorCode.USER_NOT_ACTIVE);
        }
        UserPassword userPassword=userPasswordRepository.findByUserId(user.getId());
        if(userPassword==null){
            throw new BusinessException(ErrorCode.USER_LOGIN_ERROR);
        }
        if(!passwordEncoder.matches(clearTextPassword,userPassword.getPassword())){
            throw new BusinessException(ErrorCode.USER_LOGIN_ERROR);
        }
        return user;
    }

    @Override
    public User loginByPhone(String phone, String clearTextPassword) {
        User user=userRepository.findByPhone(phone);
        return loginByUser(user,clearTextPassword);
    }

    @Override
    public void logoutUser() {

    }
}
