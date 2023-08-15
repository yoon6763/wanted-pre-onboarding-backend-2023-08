package com.wanted.preonboarding.exception.board;

import com.wanted.preonboarding.exception.user.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BoardException extends RuntimeException {

    private final BoardErrorCode boardErrorCode;
}
