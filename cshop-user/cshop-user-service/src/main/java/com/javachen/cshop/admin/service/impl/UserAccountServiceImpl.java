package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.admin.entity.User;
import com.javachen.cshop.admin.model.form.PasswordChange;
import com.javachen.cshop.admin.model.form.PasswordReset;
import com.javachen.cshop.admin.model.form.UserLogin;
import com.javachen.cshop.admin.model.form.UserRegister;
import com.javachen.cshop.admin.repository.UserRepository;
import com.javachen.cshop.admin.service.UserAccountService;
import com.javachen.cshop.admin.service.MqService;
import com.javachen.cshop.common.exception.CustomException;
import com.javachen.cshop.common.exception.ErrorCode;
import com.javachen.cshop.common.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {
    private static final String REGISTER_KEY_PREFIX = "cshop:register:";
    private static final String RESET_KEY_PREFIX = "cshop:reset:";

    private static int TEMP_PASSWORD_LENGTH = 12;

    @Resource
    protected UserRepository userRepository;

    @Resource
    protected PasswordEncoder passwordEncoderBean;

    @Autowired
    private MqService mqService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    protected String getResetPasswordURL() {
        return System.getProperty("resetPasswordURL");
    }

    @Override
    @Transactional
    public User changePassword(PasswordChange passwordChange) {
        User user = userRepository.findByUsername(passwordChange.getUsername());
        user.setPassword(passwordChange.getNewPassword());
        user = userRepository.save(user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(passwordChange.getUsername(), passwordChange.getNewPassword(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authRequest);
        auth.setAuthenticated(false);
        return user;
    }

    /**
     * 发送短信验证码
     *
     * @param phone
     */
    @Override
    public String sendVerifyCode(String phone) {
        //1.生成验证码
        String code = RandomStringUtils.randomNumeric(4);
        //2.发送短信
        mqService.sendSms(phone, code);

        log.info("phone：{}，code：{}", phone, code);

        //3.将code存入redis
        String key = REGISTER_KEY_PREFIX + phone;
        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
        if (org.springframework.util.StringUtils.isEmpty(codeCache)) {
            this.stringRedisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
        }
        return code;
    }

    @Override
    public User register(UserRegister userRegister) {
        User user = new User();
        user.setEmail(userRegister.getEmail());
        user.setPhone(userRegister.getPhone());
        user.setUsername(userRegister.getUsername());
        user.setNickname(userRegister.getNickname());

        if (userRepository.findByPhone(user.getPhone()) != null) {
            throw new CustomException(ErrorCode.USER_PHONE_ALREADY_EXIST);
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new CustomException(ErrorCode.USER_USERNAME_ALREADY_EXIST);
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new CustomException(ErrorCode.USER_EMAIL_ALREADY_EXIST);
        }

        if (!userRegister.getPassword().equals(userRegister.getPasswordConfirm())) {
            throw new CustomException(ErrorCode.USER_PASSWORD_NOT_EQUAL);
        }

//        String key = REGISTER_KEY_PREFIX + user.getPhone();
//        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
//        if (codeCache == null || !codeCache.equals(userRegister.getCode())) {
//            throw new BusinessException(ErrorCode.USER_PHONE_CODE_ERROR);
//        }

        user.setPassword(encodePassword(userRegister.getPassword()));

        //FIXME 后去需要邮件激活
        user.setEnabled(true); //未激活
        userRepository.save(user);

        mqService.sendEmail("register", user.getEmail(),null, user.getId(), user.getUsername());

        //清理redis
//            this.stringRedisTemplate.delete(key);
        return user;
    }

    @Override
    public User login(UserLogin userLogin) {
        User user = userRepository.findByUsername(userLogin.getUsername());
        if(user ==null){
            user = userRepository.findByEmail(userLogin.getUsername());
        }

        checkUser(user);

        if(isPasswordValid(user.getPassword(),userLogin.getPassword())){
            return user;
        }else{
            throw new CustomException(ErrorCode.USER_LOGIN_ERROR);
        }
    }

    @Override
    public User logout(PasswordChange passwordChange) {
        return null;
    }


    @Override
    @Transactional
    public void sendForgotUsernameNotification(String email) {
        User user = userRepository.findByEmail(email);
        checkUser(user);

        HashMap<String, Object> vars = new HashMap<String, Object>();
        vars.put("username", user.getUsername());

//        mqService.sendEmail("forgetUsername",user.getEmail(),resetPasswordUrl, user.getId(), user.getUsername());
    }

    @Override
    @Transactional
    public void sendResetPasswordNotification(String username) {
        User user = null;

        if (username != null) {
            user = userRepository.findByUsername(username);
        }

        checkUser(user);

        String token = PasswordUtils.generateSecurePassword(TEMP_PASSWORD_LENGTH);
        token = token.toLowerCase();

        HashMap<String, Object> vars = new HashMap<String, Object>();
        vars.put("token", token);
        String resetPasswordUrl = getResetPasswordURL();
        if (!StringUtils.isEmpty(resetPasswordUrl)) {
            if (resetPasswordUrl.contains("?")) {
                resetPasswordUrl = resetPasswordUrl + "&token=" + token;
            } else {
                resetPasswordUrl = resetPasswordUrl + "?token=" + token;
            }
        }
        vars.put("resetPasswordUrl", resetPasswordUrl);
        mqService.sendEmail("resetPassword", user.getEmail(), resetPasswordUrl, user.getId(), user.getUsername());
    }

    @Override
    @Transactional
    public User resetPassword(PasswordReset passwordReset) {
        User user = userRepository.findByUsername(passwordReset.getUsername());
        checkUser(user);

        String token = passwordReset.getToken().toLowerCase();

        //校验token
        String key = RESET_KEY_PREFIX + passwordReset.getUsername();
        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
        if (codeCache == null || !codeCache.equals(token)) {
            throw new CustomException(ErrorCode.USER_TOKEN_INVALID);
        }
        if (!passwordReset.getNewPassword().equals(passwordReset.getNewPasswordConfirm())) {
            throw new CustomException(ErrorCode.USER_PASSWORD_NOT_EQUAL);
        }

        user.setPassword(encodePassword(passwordReset.getNewPassword()));
        userRepository.save(user);
        return user;
    }

    protected void checkUser(User user) {
        if (user == null) {
            throw new CustomException(ErrorCode.USER_NOT_EXIST);
        }  else if (!user.isEnabled()) {
            throw new CustomException(ErrorCode.USER_NOT_ACTIVE);
        }
    }

    protected boolean isPasswordValid(String encodedPassword, String rawPassword) {
        return passwordEncoderBean.matches(rawPassword, encodedPassword);
    }

    protected String encodePassword(String rawPassword) {
        return passwordEncoderBean.encode(rawPassword);
    }
}
