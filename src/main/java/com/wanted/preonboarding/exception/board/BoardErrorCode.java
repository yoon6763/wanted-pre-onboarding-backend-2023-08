package com.wanted.preonboarding.exception.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BoardErrorCode {

    BOARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 게시글이 존재하지 않습니다."),
    BOARD_AUTHOR_NOT_MATCH(HttpStatus.BAD_REQUEST, "해당 게시글의 작성자가 아닙니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
