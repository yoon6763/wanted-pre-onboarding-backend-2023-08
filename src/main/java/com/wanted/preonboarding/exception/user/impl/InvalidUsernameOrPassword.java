package com.wanted.preonboarding.exception.user.impl;

import com.wanted.preonboarding.exception.user.UserErrorCode;
import com.wanted.preonboarding.exception.user.UserException;

public class InvalidUsernameOrPassword extends UserException {

    public InvalidUsernameOrPassword() {
        super(UserErrorCode.INVALID_USERNAME_OR_PASSWORD);
    }
}
