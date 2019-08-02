package com.javachen.cshop.admin.service.impl;

import com.javachen.cshop.admin.entity.Admin;
import com.javachen.cshop.admin.model.form.PasswordChange;
import com.javachen.cshop.admin.model.form.PasswordReset;
import com.javachen.cshop.admin.model.form.UserLogin;
import com.javachen.cshop.admin.model.form.UserRegister;
import com.javachen.cshop.admin.repository.AdminRepository;
import com.javachen.cshop.admin.service.AdminAccountService;
import com.javachen.cshop.admin.service.MqService;
import com.javachen.cshop.common.exception.BusinessException;
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
public class AdminAccountServiceImpl implements AdminAccountService {
    private static final String REGISTER_KEY_PREFIX = "cshop:register:";
    private static final String RESET_KEY_PREFIX = "cshop:reset:";


    private static int TEMP_PASSWORD_LENGTH = 12;
    private static final int FULL_PASSWORD_LENGTH = 16;

    @Resource
    protected AdminRepository adminRepository;

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
    public Admin changePassword(PasswordChange passwordChange) {
        Admin user = adminRepository.findByUsername(passwordChange.getUsername());
        user.setPassword(passwordChange.getNewPassword());
        user = adminRepository.save(user);
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
    public Admin register(UserRegister userRegister) {
        Admin admin = new Admin();
        admin.setEmail(userRegister.getEmail());
        admin.setPhone(userRegister.getPhone());
        admin.setUsername(userRegister.getUsername());
        admin.setNickName(userRegister.getNickName());

        if (adminRepository.findByPhone(admin.getPhone()) != null) {
            throw new BusinessException(ErrorCode.USER_PHONE_ALREADY_EXIST);
        }

        if (adminRepository.findByUsername(admin.getUsername()) != null) {
            throw new BusinessException(ErrorCode.USER_USERNAME_ALREADY_EXIST);
        }

        if (adminRepository.findByEmail(admin.getEmail()) != null) {
            throw new BusinessException(ErrorCode.USER_EMAIL_ALREADY_EXIST);
        }

        if (!userRegister.getPassword().equals(userRegister.getPasswordConfirm())) {
            throw new BusinessException(ErrorCode.USER_PASSWORD_NOT_EQUAL);
        }

//        String key = REGISTER_KEY_PREFIX + user.getPhone();
//        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
//        if (codeCache == null || !codeCache.equals(userRegister.getCode())) {
//            throw new BusinessException(ErrorCode.USER_PHONE_CODE_ERROR);
//        }

        admin.setPassword(encodePassword(userRegister.getPassword()));

        //FIXME 后去需要邮件激活
        admin.setActive(true); //未激活
        adminRepository.save(admin);

        mqService.sendEmail("register",admin.getEmail(),null,admin.getId(),admin.getUsername());

        //清理redis
//            this.stringRedisTemplate.delete(key);
        return admin;
    }

    @Override
    public Admin login(UserLogin userLogin) {
        Admin admin = adminRepository.findByUsername(userLogin.getUsername());
        if(admin==null){
            admin = adminRepository.findByEmail(userLogin.getUsername());
        }

        checkUser(admin);

        if(isPasswordValid(admin.getPassword(),userLogin.getPassword())){
            return admin;
        }else{
            throw new BusinessException(ErrorCode.USER_LOGIN_ERROR);
        }
    }

    @Override
    public Admin logout(PasswordChange passwordChange) {
        return null;
    }


    @Override
    @Transactional
    public void sendForgotUsernameNotification(String email) {
        Admin user = adminRepository.findByEmail(email);
        checkUser(user);

        HashMap<String, Object> vars = new HashMap<String, Object>();
        vars.put("username", user.getUsername());

//        mqService.sendEmail("forgetUsername",user.getEmail(),resetPasswordUrl, user.getId(), user.getUsername());
    }

    @Override
    @Transactional
    public void sendResetPasswordNotification(String username) {
        Admin user = null;

        if (username != null) {
            user = adminRepository.findByUsername(username);
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
    public Admin resetPassword(PasswordReset passwordReset) {
        Admin user = adminRepository.findByUsername(passwordReset.getUsername());
        checkUser(user);

        String token = passwordReset.getToken().toLowerCase();

        //校验token
        String key = RESET_KEY_PREFIX + passwordReset.getUsername();
        String codeCache = this.stringRedisTemplate.opsForValue().get(key);
        if (codeCache == null || !codeCache.equals(token)) {
            throw new BusinessException(ErrorCode.USER_TOKEN_INVALID);
        }
        if (!passwordReset.getNewPassword().equals(passwordReset.getNewPasswordConfirm())) {
            throw new BusinessException(ErrorCode.USER_PASSWORD_NOT_EQUAL);
        }

        user.setPassword(encodePassword(passwordReset.getNewPassword()));
        adminRepository.save(user);
        return user;
    }

    protected void checkUser(Admin user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_EXIST);
        }  else if (!user.isActive()) {
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
