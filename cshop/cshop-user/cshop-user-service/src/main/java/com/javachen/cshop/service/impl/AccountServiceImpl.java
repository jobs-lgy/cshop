package com.javachen.cshop.service.impl;

import com.javachen.cshop.common.exception.BusinessException;
import com.javachen.cshop.common.exception.ErrorCode;
import com.javachen.cshop.common.util.HashUtils;
import com.javachen.cshop.email.domain.EmailInfo;
import com.javachen.cshop.email.service.EmailService;
import com.javachen.cshop.entity.User;
import com.javachen.cshop.entity.UserPassword;
import com.javachen.cshop.model.form.PasswordChange;
import com.javachen.cshop.model.form.PasswordReset;
import com.javachen.cshop.model.form.UserLogin;
import com.javachen.cshop.model.form.UserRegister;
import com.javachen.cshop.repository.UserPasswordRepository;
import com.javachen.cshop.repository.UserRepository;
import com.javachen.cshop.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author june
 * @createTime 2019-06-27 10:43
 * @see
 * @since
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource(name = "registrationEmailInfo")
    protected EmailInfo registrationEmailInfo;

    @Resource(name = "forgotPasswordEmailInfo")
    protected EmailInfo forgotPasswordEmailInfo;

    @Resource(name = "changePasswordEmailInfo")
    protected EmailInfo changePasswordEmailInfo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String REGISTER_KEY_PREFIX = "cshop:register:";
    private static final String RESET_KEY_PREFIX = "cshop:reset:";

    @Override
    @Transactional
    public void sendForgotPasswordNotification(String email, String resetPasswordUrl) {
        //校验用户
        User user = userRepository.findByEmail(email);

        checkUser(user);

        //生成token
        String token = HashUtils.hashString(RandomStringUtils.random(10));

        String key = RESET_KEY_PREFIX + email;
        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(codeCache)) {
            this.stringRedisTemplate.opsForValue().set(key, token, 30, TimeUnit.MINUTES);
        }

        HashMap<String, Object> vars = new HashMap<String, Object>();
        vars.put("token", token);
        if (!org.apache.commons.lang3.StringUtils.isEmpty(resetPasswordUrl)) {
            if (resetPasswordUrl.contains("?")) {
                resetPasswordUrl = resetPasswordUrl + "&token=" + token;
            } else {
                resetPasswordUrl = resetPasswordUrl + "?token=" + token;
            }
        }
        vars.put("resetPasswordUrl", resetPasswordUrl);
        emailService.sendTemplateEmail(email, forgotPasswordEmailInfo, vars);
    }

    private void checkUser(User user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_EXIST);
        }
        if (!user.getActive()) {
            throw new BusinessException(ErrorCode.USER_NOT_ACTIVE);
        }
    }


    @Override
    public User login(UserLogin userLogin) {
        User user = userRepository.findByUsernameOrPhone(userLogin.getUsername(), userLogin.getPhone());

        checkUser(user);

        UserPassword userPassword = userPasswordRepository.findByUserId(user.getId());
        if (userPassword == null) {
            throw new BusinessException(ErrorCode.USER_LOGIN_ERROR);
        }
        if (!passwordEncoder.matches(userLogin.getPassword(), userPassword.getPassword())) {
            throw new BusinessException(ErrorCode.USER_PASSWORD_ERROR);
        }
        return user;
    }

    @Override
    @Transactional
    public User changePassword(PasswordChange passwordChange) {
        User user = userRepository.findByEmail(passwordChange.getEmail());
        UserPassword userPassword = userPasswordRepository.findByUserId(user.getId());

        if (!passwordChange.getNewPassword().equals(passwordChange.getNewPasswordConfirm())) {
            throw new BusinessException(ErrorCode.USER_PASSWORD_NOT_EQUAL);
        }

        if (!passwordEncoder.matches(passwordChange.getCurrentPassword(), userPassword.getPassword())) {
            throw new BusinessException(ErrorCode.USER_PASSWORD_ERROR);
        }

        userPassword.setPassword(passwordEncoder.encode(passwordChange.getNewPassword()));
        userPasswordRepository.save(userPassword);

        emailService.sendTemplateEmail(passwordChange.getEmail(), changePasswordEmailInfo, new HashMap<String, Object>());
        return user;
    }

    @Override
    @Transactional
    public User resetPassword(PasswordReset passwordReset) {
        //校验用户
        User user = userRepository.findByEmail(passwordReset.getEmail());

        checkUser(user);

        //校验token
        String key = RESET_KEY_PREFIX + passwordReset.getEmail();
        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
        if (codeCache == null || !codeCache.equals(passwordReset.getToken())) {
            throw new BusinessException(ErrorCode.USER_TOKEN_INVALID);
        }
        if (!passwordReset.getNewPassword().equals(passwordReset.getNewPasswordConfirm())) {
            throw new BusinessException(ErrorCode.USER_PASSWORD_NOT_EQUAL);
        }

        UserPassword userPassword = userPasswordRepository.findByUserId(user.getId());
        userPassword.setPassword(passwordEncoder.encode(passwordReset.getNewPassword()));
        userPasswordRepository.save(userPassword);

        return user;
    }

    @Override
    @Transactional
    public User register(UserRegister userRegister) {
        User newUser=userRegister.getUser();

        if (userRepository.findByPhone(newUser.getPhone()) != null) {
            throw new BusinessException(ErrorCode.USER_PHONE_ALREADY_EXIST);
        }

        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            throw new BusinessException(ErrorCode.USER_USERNAME_ALREADY_EXIST);
        }

        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            throw new BusinessException(ErrorCode.USER_EMAIL_ALREADY_EXIST);
        }

        if (!userRegister.getPassword().equals(userRegister.getPasswordConfirm())) {
            throw new BusinessException(ErrorCode.USER_PASSWORD_NOT_EQUAL);
        }

//        String key = REGISTER_KEY_PREFIX + newUser.getPhone();
//        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
//        if (codeCache == null || !codeCache.equals(userRegister.getCode())) {
//            throw new BusinessException(ErrorCode.USER_PHONE_CODE_ERROR);
//        }

        try {
            //FIXME 后去需要邮件激活
            newUser.setActive(true); //未激活
            userRepository.save(newUser);

            UserPassword userPassword = new UserPassword();
            userPassword.setUserId(newUser.getId());
            userPassword.setPassword(passwordEncoder.encode(userRegister.getPassword()));
            userPasswordRepository.save(userPassword);

            HashMap<String, Object> vars = new HashMap<String, Object>();
            vars.put("user", newUser);

            //发送注册成功邮件
            registrationEmailInfo.setUserId(newUser.getId());
            registrationEmailInfo.setType(EmailInfo.REGISTER_TYPE);
            emailService.sendTemplateEmail(newUser.getEmail(), registrationEmailInfo, vars);

            //清理redis
//            this.stringRedisTemplate.delete(key);

            return newUser;
        } catch (Exception e) {
            log.error("用户注册失败", e);
            throw new BusinessException(ErrorCode.USER_REGISTER_FAIL);
        }
    }

    /**
     * 发送短信验证码
     *
     * @param phone
     */
    @Override
    public void sendVerifyCode(String phone) {
        //1.生成验证码
        String code = RandomStringUtils.randomNumeric(4);
        //2.发送短信
        try {
            Map<String, String> msg = new HashMap<>();
            msg.put("phone", phone);
            msg.put("code", code);
            this.amqpTemplate.convertAndSend("cshop.sms.exchange", "cshop.verify.code", msg);

            //3.将code存入redis
            String key = REGISTER_KEY_PREFIX + phone;
            String codeCache = this.stringRedisTemplate.opsForValue().get(key);
            if (StringUtils.isEmpty(codeCache)) {
                this.stringRedisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
            }
        } catch (AmqpException e) {
            log.error("发送短信失败，phone：{}，code：{}", phone, code);
            throw new BusinessException(ErrorCode.USER_PHONE_CODE_SEND_FAIL);
        }

    }
}
