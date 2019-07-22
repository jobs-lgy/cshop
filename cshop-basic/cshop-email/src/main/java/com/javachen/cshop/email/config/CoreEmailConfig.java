package com.javachen.cshop.email.config;

import com.javachen.cshop.email.domain.EmailInfo;
import com.javachen.cshop.email.message.MessageCreator;
import com.javachen.cshop.email.message.impl.ThymeleafMessageCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

/**
 * @author june
 * @createTime 2019-06-27 12:39
 * @see
 * @since
 */
@Configuration
public class CoreEmailConfig {

    @Value("${spring.mail.username}")
    private String fromAddress;

    @Bean
    @Autowired
    public MessageCreator blMessageCreator(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        return new ThymeleafMessageCreator(templateEngine, javaMailSender);
    }

    public EmailInfo emailInfo() {
        EmailInfo info = new EmailInfo();
        info.setType(1);
        info.setFromAddress(fromAddress);
        info.setSendAsyncPriority("2");
        info.setSendEmailReliableAsync("false");
        return info;
    }

    public EmailInfo changePasswordEmailInfo() {
        EmailInfo info = emailInfo();
        info.setSubject("密码修改成功");
        info.setTemplate("changePassword-email");
        return info;
    }

    public EmailInfo resetPasswordEmailInfo() {
        EmailInfo info = emailInfo();
        info.setSubject("重置密码");
        info.setTemplate("resetPassword-email");
        return info;
    }

    public EmailInfo registrationEmailInfo() {
        EmailInfo info = emailInfo();
        info.setType(EmailInfo.REGISTER_TYPE);
        info.setSubject("注册成功");
        info.setTemplate("register-email");
        return info;
    }
}
