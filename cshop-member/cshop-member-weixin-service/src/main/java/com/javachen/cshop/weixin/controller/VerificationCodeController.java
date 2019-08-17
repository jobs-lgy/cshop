package com.javachen.cshop.weixin.controller;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.weixin.service.VerificationCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
public class VerificationCodeController {
    @Resource
    private VerificationCodeService verificationCodeService;

    @GetMapping("/weixin/verify")
    public RestResponse<Boolean> verify(@NotNull @RequestParam(value = "phone", required = true) String phone,
                                        @NotNull @RequestParam(value = "code", required = true) String code) {
        return RestResponse.success(verificationCodeService.verify(phone, code));
    }
}