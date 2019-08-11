package com.javachen.cshop.member.service;

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
    public Member findById(@PathVariable("id") Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "/member/wechat/{openId}")
    public Member findByOpenId(@PathVariable("openId") String openId) {
        return memberRepository.findByOpenId(openId);
    }
}
