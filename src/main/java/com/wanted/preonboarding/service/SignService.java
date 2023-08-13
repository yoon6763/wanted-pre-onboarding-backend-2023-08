package com.wanted.preonboarding.service;

import com.wanted.preonboarding.data.user.dto.SignInResultDto;
import com.wanted.preonboarding.data.user.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String email, String password);
    SignInResultDto signIn(String email, String password) throws RuntimeException;
}