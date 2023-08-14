package com.wanted.preonboarding.exception.user.impl;

import com.wanted.preonboarding.exception.user.UserErrorCode;
import com.wanted.preonboarding.exception.user.UserException;

public class EmailDuplicateException extends UserException {

    public EmailDuplicateException() {
        super(UserErrorCode.EMAIL_DUPLICATE);
    }
}
