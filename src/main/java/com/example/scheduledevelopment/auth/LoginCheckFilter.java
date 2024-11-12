package com.example.scheduledevelopment.auth;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/register/", "/api/login", "/api/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            log.info("인증 체크 필터 시작  {}", requestURI);
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체코 로직 실행 {}", requestURI);
                HttpSession session = request.getSession(false);

                if (session == null || session.getAttribute("email") == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
                    log.info("미인증 사용자 요청 {} ", requestURI);
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException e) {
            log.error("필터 처리 중 예외 발생: {}", e.getMessage(), e);
            throw e;
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
