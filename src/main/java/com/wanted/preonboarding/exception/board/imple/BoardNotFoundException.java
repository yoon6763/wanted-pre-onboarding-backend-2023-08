package com.wanted.preonboarding.exception.board.imple;

import com.wanted.preonboarding.exception.board.BoardErrorCode;
import com.wanted.preonboarding.exception.board.BoardException;
import com.wanted.preonboarding.exception.user.UserErrorCode;
import com.wanted.preonboarding.exception.user.UserException;

public class BoardNotFoundException extends BoardException {

    public BoardNotFoundException() {
        super(BoardErrorCode.BOARD_NOT_FOUND);
    }
}
