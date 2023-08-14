package com.wanted.preonboarding.service;

import com.wanted.preonboarding.data.board.Board;
import com.wanted.preonboarding.data.board.dto.BoardInfoDto;
import com.wanted.preonboarding.data.board.dto.BoardRequestDto;
import com.wanted.preonboarding.data.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    BoardInfoDto getBoard(Long boardId);

    void createBoard(String token, BoardRequestDto boardRequestDto);

    void updateBoard(String token, Long boardId, BoardRequestDto boardRequestDto);

    void deleteBoard(String token, Long boardId);

    Page<BoardInfoDto> getPosts(Pageable pageable);
}
