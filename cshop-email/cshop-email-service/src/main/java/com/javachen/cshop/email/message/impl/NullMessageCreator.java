package com.javachen.cshop.email.message.impl;

import com.javachen.cshop.email.domain.EmailInfo;
import com.javachen.cshop.email.domain.EmailTarget;
import com.javachen.cshop.email.message.MessageCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

@Slf4j
public class NullMessageCreator extends MessageCreator {
    public NullMessageCreator(JavaMailSender javaMailSender) {
        super(javaMailSender);
    }

    @Override
    public String buildMessageBody(String template, Map<String, Object> props) {
        return template;
    }

    @Override
    public void sendMessage(EmailInfo emailInfo, EmailTarget emailTarget, final Map<String, Object> props) throws MailException {
        log.warn("NullMessageCreator is defined -- specify a real message creator to send emails");
    }

}
