package com.javachen.cshop.weixin.feign;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.member.entity.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("cshop-member-service")
public interface MemberServiceClient {
    @GetMapping(value = "/member/phone/{phone}")
    RestResponse<Member> findByPhone(@PathVariable("phone") String phone);
}
