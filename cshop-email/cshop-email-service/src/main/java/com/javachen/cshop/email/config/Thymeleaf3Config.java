package com.javachen.cshop.email.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * @author june
 * @createTime 2019-06-27 14:53
 * @see
 * @since
 */
@Component
public class Thymeleaf3Config {
    @Bean
    public SpringTemplateEngine emailTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(emailTemplateResolver());
        return templateEngine;
    }

    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("emailTemplates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode("HTML5");
        emailTemplateResolver.setCharacterEncoding("UTF-8");
        emailTemplateResolver.setOrder(1);

        return emailTemplateResolver;
    }

}
