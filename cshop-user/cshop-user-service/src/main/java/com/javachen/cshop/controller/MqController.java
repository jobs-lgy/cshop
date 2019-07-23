package com.javachen.cshop.controller;

import com.javachen.cshop.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author june
 * @createTime 2019-07-22 15:49
 * @see
 * @since
 */
@RestController
public class MqController {
    @Autowired
    private MqService mqService;

    @GetMapping("/sms/{phone}/{code}")
    @Validated
    public Boolean sendSms(@PathVariable String phone,@PathVariable String code) {
        mqService.sendSms(phone,code);
        return true;
    }

    @GetMapping("/email")
    @Validated
    public Boolean sendEmail() {
        mqService.sendEmail("register","junecloud@163.com",null,1L,"javachen");
        return true;
    }
}
