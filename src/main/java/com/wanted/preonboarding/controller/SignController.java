package com.wanted.preonboarding.controller;

import com.wanted.preonboarding.entity.common.MessageResponse;
import com.wanted.preonboarding.entity.user.dto.SignInResultDto;
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
        LOGGER.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", email, signInResultDto.getToken());

        return signInResultDto;
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<Object> signUp(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {

        LOGGER.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****", email);

        signService.signUp(email, password);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("회원가입 성공"));
    }
}