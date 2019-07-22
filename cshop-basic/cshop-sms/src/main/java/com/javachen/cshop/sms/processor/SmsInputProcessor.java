package com.javachen.cshop.sms.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author june
 * @createTime 2019-07-22 14:52
 * @see
 * @since
 */
public interface SmsInputProcessor {
    String INPUT = "smsInput";

    @Input(INPUT)
    SubscribableChannel smsInput();
}
