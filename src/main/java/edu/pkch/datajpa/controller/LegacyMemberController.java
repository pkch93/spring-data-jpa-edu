package edu.pkch.datajpa.controller;

import edu.pkch.datajpa.postgresql.LegacyMember;
import edu.pkch.datajpa.postgresql.LegacyMemberRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/legacy/member")
public class LegacyMemberController {
    private final LegacyMemberRepository legacyMemberRepository;

    public LegacyMemberController(LegacyMemberRepository legacyMemberRepository) {
        this.legacyMemberRepository = legacyMemberRepository;
    }

    @PostMapping
    public void createLegacyMember() {
        legacyMemberRepository.save(
                LegacyMember.builder()
                        .nickname("legacy-member")
                        .build()
        );
    }
}
