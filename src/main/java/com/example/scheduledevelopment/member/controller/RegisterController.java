package com.example.scheduledevelopment.member.controller;

import com.example.scheduledevelopment.member.dto.MemberDto;
import com.example.scheduledevelopment.member.service.MemberServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final MemberServiceImpl memberServiceImpl;

    public RegisterController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @PostMapping("/")
    public ResponseEntity<?> createMember(
            @RequestBody MemberDto memberDto
    ) {
        try {
            MemberDto createMember = memberServiceImpl.createMember(memberDto);
            return ResponseEntity.ok(createMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("회원 생성 실패!");
        }
    }
}
