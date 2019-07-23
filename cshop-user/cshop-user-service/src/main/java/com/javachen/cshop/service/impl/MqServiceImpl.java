package com.javachen.cshop.service.impl;

import com.google.common.collect.Maps;
import com.javachen.cshop.common.utils.json.ObjectMapperUtils;
import com.javachen.cshop.processor.EmailOutputProcessor;
import com.javachen.cshop.processor.SmsOutputProcessor;
import com.javachen.cshop.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class MqServiceImpl implements MqService {

    @Autowired
    SmsOutputProcessor smsOutputProcessor;

    @Autowired
    EmailOutputProcessor emailOutputProcessor;

    @Async
    public void sendEmail(String emailType, String emailTo, String url ,Long userId,String username) {
        Map map= Maps.newHashMap();
        map.put("emailType",emailType);
        map.put("emailTo",emailTo);
        map.put("url",url);
        map.put("userId",userId);
        map.put("username",username);
        emailOutputProcessor.emailOutput().send(MessageBuilder.withPayload(ObjectMapperUtils.mapToJson(map)).build());
    }

    @Async
    public void sendSms(String phone,String code){
        Map map= Maps.newHashMap();
        map.put("code",code);
        map.put("phone",phone);
        smsOutputProcessor.smsOutput().send(MessageBuilder.withPayload(ObjectMapperUtils.mapToJson(map)).build());
    }
}
