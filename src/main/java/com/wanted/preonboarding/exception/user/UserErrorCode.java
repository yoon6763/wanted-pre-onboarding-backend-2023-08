package com.wanted.preonboarding.exception.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode {

    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "이미 가입된 이메일입니다."),
    PASSWORD_TOO_SHORT(HttpStatus.BAD_REQUEST, "비밀번호는 8자 이상이어야 합니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 회원이 존재하지 않습니다."),
    INVALID_USERNAME_OR_PASSWORD(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다."),
    INVALID_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
