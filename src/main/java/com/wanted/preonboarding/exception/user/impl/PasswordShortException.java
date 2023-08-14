package com.wanted.preonboarding.exception.user.impl;

import com.wanted.preonboarding.exception.user.UserErrorCode;
import com.wanted.preonboarding.exception.user.UserException;

public class PasswordShortException extends UserException {

    public PasswordShortException() {
        super(UserErrorCode.PASSWORD_TOO_SHORT);
    }
}
