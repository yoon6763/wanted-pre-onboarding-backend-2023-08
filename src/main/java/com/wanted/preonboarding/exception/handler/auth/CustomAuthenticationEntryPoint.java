package com.wanted.preonboarding.exception.handler.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.preonboarding.entity.common.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("[commence] 인증 실패로 response.sendError 발생");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(401)
                .errorType("Unauthorized")
                .path(httpServletRequest.getRequestURI())
                .build();

        httpServletResponse.setStatus(401);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}