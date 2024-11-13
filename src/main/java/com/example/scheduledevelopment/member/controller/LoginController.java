package com.example.scheduledevelopment.member.controller;

import com.example.scheduledevelopment.member.dto.LoginRequest;
import com.example.scheduledevelopment.member.service.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final MemberServiceImpl memberServiceImpl;

    public LoginController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request) {
        boolean isAuthenticate = memberServiceImpl.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticate) {
            HttpSession session = request.getSession(true);
            session.setAttribute("email", loginRequest.getEmail());
            return ResponseEntity.ok("로그인 성공!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 이메일 혹은 비밀번호 입니다.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); //세션 무효화
            return ResponseEntity.ok("로그아웃 성공!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그아웃 실패!");
        }
    }
}
