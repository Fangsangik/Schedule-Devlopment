package com.example.scheduledevelopment.member.controller;

import com.example.scheduledevelopment.member.dto.MemberDto;
import com.example.scheduledevelopment.member.service.MemberServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> updateMember
            (@PathVariable("memberId") Long memberId,
             @RequestBody MemberDto memberDto) {
        try {
            MemberDto updatedMember = memberServiceImpl.updateMember(memberId, memberDto);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("회원 update 실패");
        }
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable("memberId") Long memberId) {
        try {
            MemberDto memberById = memberServiceImpl.findMemberById(memberId);
            return ResponseEntity.ok(memberById);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("회원 조회에 실패했습니다.");
        }
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable("memberId") Long memberId) {
        try {
            memberServiceImpl.deleteMemberById(memberId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("삭제 실패");
        }
    }
}
