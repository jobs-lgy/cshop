package com.javachen.cshop.sms.listener;

import com.javachen.cshop.sms.pojo.SmsProperties;
import com.javachen.cshop.sms.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
@Slf4j
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsProperties smsProperties;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "cshop.sms.queue",durable = "true"),
            exchange = @Exchange(value = "cshop.sms.exchange",ignoreDeclarationExceptions = "true"),
            key = {"cshop.verify.code"}
    ))
    public void listenSms(Map<String,String> msg){
        if (msg == null || msg.size() <= 0){
            //不做处理
            return;
        }
        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)){
            //不做处理
            return;
        }

        log.info("短信发送成功：phone：{},code:{}",phone,code);
        //FIXME 调用短信接口的配置不正确，需要去阿里云申请
//        try {
//            SendSmsResponse response = this.smsUtils.sendSms(phone, code, smsProperties.getSignName(), smsProperties.getVerifyCodeTemplate());
//        }catch (ClientException e){
//            log.error("发送短信失败",e);
//            return;
//        }
    }
}
