package edu.pkch.datajpa.controller;

import edu.pkch.datajpa.mysql.Member;
import edu.pkch.datajpa.mysql.MemberRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping
    public void createMember() {
        memberRepository.save(
                Member.builder()
                        .nickname("member")
                        .build()
        );
    }
}
