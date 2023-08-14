package com.wanted.preonboarding.exception.user.impl;

import com.wanted.preonboarding.exception.user.UserErrorCode;
import com.wanted.preonboarding.exception.user.UserException;

public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
