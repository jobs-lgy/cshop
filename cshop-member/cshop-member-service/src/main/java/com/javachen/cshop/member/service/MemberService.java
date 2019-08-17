package com.javachen.cshop.member.service;

import com.javachen.cshop.common.model.response.RestResponse;
import com.javachen.cshop.member.entity.Member;
import com.javachen.cshop.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(value = "/member/{id}")
    public RestResponse<Member> findById(@PathVariable("id") Long id) {
        return RestResponse.success(memberRepository.findById(id).orElse(null));
    }

    @GetMapping(value = "/member/phone/{phone}")
    public RestResponse<Member> findByPhone(@PathVariable("phone") String phone) {
        return RestResponse.success(memberRepository.findByPhone(phone));
    }

    @GetMapping(value = "/member/openid/{openid}")
    public Member findByOpenId(@PathVariable("openid") String openid) {
        return memberRepository.findByOpenid(openid);
    }
}
