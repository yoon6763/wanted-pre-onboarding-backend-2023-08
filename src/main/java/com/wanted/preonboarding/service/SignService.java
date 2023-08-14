package com.wanted.preonboarding.service;

import com.wanted.preonboarding.entity.user.dto.SignInResultDto;

public interface SignService {
    void signUp(String email, String password);
    SignInResultDto signIn(String email, String password) throws RuntimeException;
}