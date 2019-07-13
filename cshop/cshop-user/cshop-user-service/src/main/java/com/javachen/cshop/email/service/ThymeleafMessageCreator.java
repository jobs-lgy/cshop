package com.javachen.cshop.email.service;

import com.javachen.cshop.email.domain.EmailInfo;
import com.javachen.cshop.email.service.message.MessageCreator;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Iterator;
import java.util.Map;

/**
 * @author june
 * @createTime 2019-06-27 14:13
 * @see
 * @since
 */
public class ThymeleafMessageCreator extends MessageCreator {

    private TemplateEngine templateEngine;

    public ThymeleafMessageCreator(TemplateEngine templateEngine, JavaMailSender mailSender) {
        super(mailSender);
        this.templateEngine = templateEngine;
    }

    @Override
    public String buildMessageBody(EmailInfo info, Map<String,Object> props) {
        final Context thymeleafContext = new Context();
        if (props != null) {
            Iterator<String> propsIterator = props.keySet().iterator();
            while(propsIterator.hasNext()) {
                String key = propsIterator.next();
                thymeleafContext.setVariable(key, props.get(key));
            }
        }

        return this.templateEngine.process( info.getTemplate(), thymeleafContext);
    }
}
