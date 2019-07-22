package com.javachen.cshop.sms.service;

import com.javachen.cshop.common.utils.json.ObjectMapperUtils;
import com.javachen.cshop.sms.processor.SmsInputProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author june
 * @createTime 2019-07-22 14:10
 * @see
 * @since
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @StreamListener(SmsInputProcessor.INPUT)
    @Override
    public void receive(Message<String> message) {
        String phone = null;
        String code = null;
        try {
            Map<String, Object> smsMap = ObjectMapperUtils.json2map(message.getPayload());
            if (smsMap == null || !smsMap.containsKey("code") || !smsMap.containsKey("phone")) {
                log.warn("Received msg not validated:{}", message.getPayload());
                return;
            }
            phone = smsMap.get("phone").toString();
            code = smsMap.get("code").toString();
            //FIXME 调用短信接口的配置不正确，需要去阿里云申请
            //            SendSmsResponse response = this.smsUtils.sendSms(phone, code, smsProperties.getSignName(), smsProperties.getVerifyCodeTemplate());
            log.info("Sending sms success,phone={},code={}", phone, code);
        } catch (Exception e) {
            log.error("Sending sms fail,phone={},code={}", phone, code, e);
        }

    }
}
