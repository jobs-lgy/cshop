package com.javachen.cshop.email.service;

import com.javachen.cshop.email.domain.*;
import com.javachen.cshop.email.exception.EmailException;
import com.javachen.cshop.email.repository.EmailClickRepository;
import com.javachen.cshop.email.repository.EmailOpenRepository;
import com.javachen.cshop.email.repository.EmailRecordRepository;
import com.javachen.cshop.email.service.message.EmailServiceProducer;
import com.javachen.cshop.email.service.message.MessageCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    protected ServerInfo serverInfo;

    @Autowired
    protected EmailServiceProducer emailServiceProducer;

    @Autowired
    protected MessageCreator messageCreator;

    @Autowired
    protected EmailRecordRepository emailTrackingRepository;

    @Autowired
    protected EmailClickRepository emailClickRepository;

    @Autowired
    protected EmailOpenRepository emailOpenRepository;

    @Override
    @Transactional
    public boolean sendTemplateEmail(EmailTarget emailTarget, EmailInfo emailInfo, Map<String, Object> props) {
        if (props == null) {
            props = new HashMap<String, Object>();
        }

        //保存
        EmailRecord emailRecord = new EmailRecord();
        emailRecord.setSource(emailInfo.getSourceAddress());
        emailRecord.setTarget(emailTarget.getTargetAddress());
        emailRecord.setSentTime(new Date());
        emailRecord.setType(emailInfo.getType());
        emailRecord.setUserId(emailInfo.getUserId());
        emailTrackingRepository.save(emailRecord);

        props.put("emailRecordId", emailRecord.getId());

        return sendBasicEmail(emailInfo, emailTarget, props);
    }

    @Override
    public boolean sendTemplateEmail(String email, EmailInfo emailInfo, Map<String, Object> props) {
        EmailTarget emailTarget = new EmailTarget();
        emailTarget.setTargetAddress(email);
        return sendTemplateEmail(emailTarget, emailInfo, props);
    }

    @Override
    public boolean sendBasicEmail(EmailInfo emailInfo, EmailTarget emailTarget, Map<String, Object> props) {
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        props.put("info", emailInfo);
        props.put("target", emailTarget);

        if (Boolean.parseBoolean(emailInfo.getSendEmailReliableAsync())) {
            if (emailServiceProducer == null) {
                throw new EmailException("The property sendEmailReliableAsync on EmailInfo is true, but the EmailService does not have an instance of JMSEmailServiceProducer set.");
            }
            emailServiceProducer.send(props);
        } else {
            messageCreator.sendMessage(props);
        }

        return true;
    }

    @Override
    @Transactional
    public void emailOpened(EmailOpen emailOpen) {
        emailOpenRepository.save(emailOpen);
    }

    @Override
    @Transactional
    public void emailClicked(EmailClick emailClick) {

    }

}
