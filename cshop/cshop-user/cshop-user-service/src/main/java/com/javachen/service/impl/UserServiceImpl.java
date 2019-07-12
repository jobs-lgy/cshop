package com.javachen.service.impl;

import com.javachen.common.exception.BusinessException;
import com.javachen.common.exception.ErrorCode;
import com.javachen.common.util.CodeUtil;
import com.javachen.email.domain.EmailInfo;
import com.javachen.email.service.EmailService;
import com.javachen.entity.User;
import com.javachen.entity.UserPassword;
import com.javachen.repository.UserPasswordRepository;
import com.javachen.repository.UserRepository;
import com.javachen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource(name="registrationEmailInfo")
    protected EmailInfo registrationEmailInfo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX = "cshop:register:";

    @Override
    @Cacheable("byPhone")
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    @Transactional
    public void register(User user, String password, String passwordConfirm,String code) {
        if(userRepository.findByPhone(user.getPhone()) !=null){
            throw new BusinessException(ErrorCode.USER_PHONE_ALREADY_EXIST);
        }

        if(userRepository.findByUsername(user.getUsername()) !=null){
            throw new BusinessException(ErrorCode.USER_USERNAME_ALREADY_EXIST);
        }

        if(userRepository.findByEmail(user.getEmail()) !=null){
            throw new BusinessException(ErrorCode.USER_EMAIL_ALREADY_EXIST);
        }

        if(! password.equals(passwordConfirm)){
            throw new BusinessException(ErrorCode.USER_PASSWORD_NOT_EQUAL);
        }

        String key = KEY_PREFIX + user.getPhone();
        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
        if(codeCache==null || !codeCache.equals(code)){
            throw new BusinessException(ErrorCode.USER_PHONE_CODE_ERROR);
        }

        try {
            //FIXME 后去需要邮件激活
            user.setActive(true); //未激活
            userRepository.save(user);

            UserPassword userPassword=new UserPassword();
            userPassword.setUserId(user.getId());
            userPassword.setPassword(passwordEncoder.encode(password));
            userPasswordRepository.save(userPassword);

            HashMap<String, Object> vars = new HashMap<String, Object>();
            vars.put("user", user);

            //发送注册成功邮件
            registrationEmailInfo.setUserId(user.getId());
            registrationEmailInfo.setType(EmailInfo.REGISTER_TYPE);
            emailService.sendTemplateEmail(user.getEmail(), registrationEmailInfo, vars);

            //清理redis
            this.stringRedisTemplate.delete(KEY_PREFIX + user.getPhone());
        } catch (Exception e) {
            log.error("用户注册失败",e);
            throw new BusinessException(ErrorCode.USER_REGISTER_FAIL);
        }
    }

    /**
     * 发送短信验证码
     * @param phone
     */
    @Override
    public void sendVerifyCode(String phone) {
        //1.生成验证码
        String code = CodeUtil.generateCode();

        Map<String,String> msg = new HashMap<>();
        msg.put("phone",phone);
        msg.put("code",code);
//        //2.发送短信
        try {
            this.amqpTemplate.convertAndSend("cshop.sms.exchange","cshop.verify.code",msg);

            //3.将code存入redis
            String key = KEY_PREFIX + phone;
            String codeCache = this.stringRedisTemplate.opsForValue().get(key);
            if(StringUtils.isEmpty(codeCache)){
                this.stringRedisTemplate.opsForValue().set(key,code,5, TimeUnit.MINUTES);
            }
        } catch (AmqpException e) {
            log.error("发送短信失败，phone：{}，code：{}",phone,code);
            throw new BusinessException(ErrorCode.USER_PHONE_CODE_SEND_FAIL);
        }

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
}
