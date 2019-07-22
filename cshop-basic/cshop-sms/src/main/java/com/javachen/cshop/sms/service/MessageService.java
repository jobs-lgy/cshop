package com.javachen.cshop.sms.service;

import org.springframework.messaging.Message;

/**
 * @author june
 * @createTime 2019-07-22 14:09
 * @see
 * @since
 */
public interface MessageService {
    public void receive(Message<String> message);
}