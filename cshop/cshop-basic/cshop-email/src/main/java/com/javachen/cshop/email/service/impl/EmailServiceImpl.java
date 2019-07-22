package com.javachen.cshop.email.service.impl;

import com.javachen.cshop.common.utils.json.ObjectMapperUtils;
import com.javachen.cshop.email.config.CoreEmailConfig;
import com.javachen.cshop.email.domain.EmailInfo;
import com.javachen.cshop.email.domain.EmailTarget;
import com.javachen.cshop.email.entity.EmailClick;
import com.javachen.cshop.email.entity.EmailOpen;
import com.javachen.cshop.email.entity.EmailRecord;
import com.javachen.cshop.email.message.MessageCreator;
import com.javachen.cshop.email.processor.EmailInputProcessor;
import com.javachen.cshop.email.repository.EmailClickRepository;
import com.javachen.cshop.email.repository.EmailOpenRepository;
import com.javachen.cshop.email.repository.EmailRecordRepository;
import com.javachen.cshop.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    protected MessageCreator messageCreator;

    @Autowired
    protected EmailRecordRepository emailTrackingRepository;

    @Autowired
    protected EmailClickRepository emailClickRepository;

    @Autowired
    protected EmailOpenRepository emailOpenRepository;

    @Autowired
    private CoreEmailConfig coreEmailConfig;

    @Transactional
    @Override
    public boolean sendEmail(EmailInfo emailInfo, EmailTarget emailTarget, Map<String, Object> props) {
        if (props == null) {
            props = new HashMap<String, Object>();
        }

        //1、保存记录
        EmailRecord emailRecord = new EmailRecord();
        emailRecord.setSource(emailInfo.getFromAddress());
        emailRecord.setTarget(emailTarget.getToAddress());
        emailRecord.setSentTime(new Date());
        emailRecord.setType(emailInfo.getType());
        emailRecord.setUserId(emailInfo.getUserId());
        emailTrackingRepository.save(emailRecord);
        props.put("emailRecordId", emailRecord.getId());

        messageCreator.sendMessage(emailInfo,emailTarget,props);

        return true;
    }

    @StreamListener(EmailInputProcessor.INPUT)
    @Override
    @Transactional
    public void receive(Message<String> message) {
        log.info("receive message:{}",message.getPayload());
        String emailType = null;
        String emailTo = null;
        EmailInfo emailInfo = null;
        try {
            Map<String, Object> props = ObjectMapperUtils.json2map(message.getPayload());
            if (props == null || !props.containsKey("emailType") || !props.containsKey("emailTo")) {
                log.warn("Received msg not validated:{}", message.getPayload());
                return;
            }
            emailType = props.get("emailType").toString();
            emailTo = props.get("emailTo").toString();

            emailInfo = getEmailInfoByType(emailType);
            EmailTarget emailTarget = new EmailTarget();
            emailTarget.setToAddress(emailTo);

            sendEmail(emailInfo, emailTarget, props);
            log.info("Sending email success,from={},to={}", emailInfo.getFromAddress(), emailTo);
        } catch (Exception e) {
            log.error("Sending email fail,from={},to={}", emailInfo.getFromAddress(), emailTo, e);
        }
    }

    private EmailInfo getEmailInfoByType(String emailType) {
        EmailInfo emailInfo = null;
        if (emailType.equals("register")) {
            emailInfo = coreEmailConfig.registrationEmailInfo();
        } else if (emailType.equals("changePassword")) {
            emailInfo = coreEmailConfig.changePasswordEmailInfo();
        } else if (emailType.equals("resetPassword")) {
            emailInfo = coreEmailConfig.resetPasswordEmailInfo();
        }
        return emailInfo;
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
