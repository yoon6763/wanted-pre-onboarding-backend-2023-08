package com.wanted.preonboarding.controller;

import com.wanted.preonboarding.data.user.dto.SignInResultDto;
import com.wanted.preonboarding.data.user.dto.SignUpResultDto;
import com.wanted.preonboarding.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sign-api")
class SignController {
    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping(value = "/sign-in")
    public SignInResultDto signIn(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) throws RuntimeException {
        LOGGER.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", email);
        SignInResultDto signInResultDto = signService.signIn(email, password);

        if (signInResultDto.getCode() == 0) {
            LOGGER.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", email, signInResultDto.getToken());
        }
        return signInResultDto;
    }

    @PostMapping(value = "/sign-up")
    public SignUpResultDto signUp(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {

        LOGGER.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****", email);
        SignUpResultDto signUpResultDto = signService.signUp(email, password);

        LOGGER.info("[signUp] 회원가입을 완료했습니다. id : {}", email);
        return signUpResultDto;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}