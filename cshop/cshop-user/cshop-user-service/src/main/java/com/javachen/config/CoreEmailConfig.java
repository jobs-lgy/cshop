package com.javachen.config;

import com.javachen.email.domain.EmailInfo;
import com.javachen.email.service.ThymeleafMessageCreator;
import com.javachen.email.service.message.MessageCreator;
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
    private String sourceAddress;

    @Bean
    @Autowired
    public MessageCreator blMessageCreator(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        return new ThymeleafMessageCreator(templateEngine, javaMailSender);
    }

    @Bean
    public EmailInfo emailInfo() {
        EmailInfo info = new EmailInfo();
        info.setType(1);
        info.setSourceAddress(sourceAddress);
        info.setSendAsyncPriority("2");
        info.setSendEmailReliableAsync("false");
        return info;
    }

    @Bean(name="changePasswordEmailInfo")
    public EmailInfo changePasswordEmailInfo() {
        EmailInfo info = emailInfo();
        info.setSubject("You have changed your password!");
        info.setTemplate("changePassword-email");
        return info;
    }

    @Bean(name="forgotPasswordEmailInfo")
    public EmailInfo forgotPasswordEmailInfo() {
        EmailInfo info = emailInfo();
        info.setSubject("Reset password request");
        info.setTemplate("resetPassword-email");
        return info;
    }

    @Bean(name="registrationEmailInfo")
    public EmailInfo registrationEmailInfo() {
        EmailInfo info = emailInfo();
        info.setType(EmailInfo.REGISTER_TYPE);
        info.setSubject("You have successfully registered!");
        info.setTemplate("register-email");
        return info;
    }
}
