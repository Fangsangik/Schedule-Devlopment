package com.example.scheduledevelopment.member.controller;

import com.example.scheduledevelopment.member.dto.MemberDto;
import com.example.scheduledevelopment.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final MemberService memberService;

    public RegisterController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createMember(
            @RequestBody MemberDto memberDto
    ) {
        try {
            MemberDto createMember = memberService.createMember(memberDto);
            return ResponseEntity.ok(createMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("회원 생성 실패!");
        }
    }
}
