package com.javachen.cshop.controller;

import com.javachen.cshop.common.model.response.CommonResponse;
import com.javachen.cshop.model.vo.UserVo;
import com.javachen.cshop.service.RabbitService;
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
public class RabbitController {
    @Autowired
    private RabbitService rabbitService;

    @GetMapping("/sms/{phone}/{code}")
    @Validated
    public CommonResponse<UserVo> sendSms(@PathVariable String phone,@PathVariable String code) {
        rabbitService.sendSms(phone,code);
        return CommonResponse.success();
    }

    @GetMapping("/email")
    @Validated
    public CommonResponse<UserVo> sendEmail() {
        rabbitService.sendEmail("register","junecloud@163.com",null,"javachen");
        return CommonResponse.success();
    }
}
