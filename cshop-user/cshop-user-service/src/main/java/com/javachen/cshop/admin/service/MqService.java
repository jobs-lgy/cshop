package com.javachen.cshop.admin.service;

/**
 * @author june
 * @createTime 2019-07-22 12:16
 * @see
 * @since
 */
public interface MqService {
    void sendEmail(String emailType, String emailTo, String url, Long userId, String username);

    void sendSms(String phone, String code);
}
