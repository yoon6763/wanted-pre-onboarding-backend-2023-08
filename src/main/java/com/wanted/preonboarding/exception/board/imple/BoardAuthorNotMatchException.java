package com.wanted.preonboarding.exception.board.imple;

import com.wanted.preonboarding.exception.board.BoardErrorCode;
import com.wanted.preonboarding.exception.board.BoardException;

public class BoardAuthorNotMatchException extends BoardException {

    public BoardAuthorNotMatchException() {
        super(BoardErrorCode.BOARD_AUTHOR_NOT_MATCH);
    }
}
