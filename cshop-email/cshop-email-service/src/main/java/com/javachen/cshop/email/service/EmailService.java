package com.javachen.cshop.email.service;

import com.javachen.cshop.email.domain.EmailInfo;
import com.javachen.cshop.email.domain.EmailTarget;
import com.javachen.cshop.email.entity.EmailClick;
import com.javachen.cshop.email.entity.EmailOpen;
import org.springframework.messaging.Message;

import java.util.Map;

public interface EmailService {

    public boolean sendEmail(EmailInfo emailInfo, EmailTarget emailTarget, Map<String, Object> props);

    void emailOpened(EmailOpen emailOpen);

    void emailClicked(EmailClick emailClick);

    public void receive(Message<String> message);
}
