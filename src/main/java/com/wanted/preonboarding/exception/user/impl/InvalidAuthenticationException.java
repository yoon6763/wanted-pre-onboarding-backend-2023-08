package com.wanted.preonboarding.exception.user.impl;


import com.wanted.preonboarding.exception.user.UserErrorCode;
import com.wanted.preonboarding.exception.user.UserException;

public class InvalidAuthenticationException extends UserException {

    public InvalidAuthenticationException() {
        super(UserErrorCode.INVALID_AUTHENTICATION);
    }
}
