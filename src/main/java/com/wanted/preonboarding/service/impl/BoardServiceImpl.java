package com.wanted.preonboarding.service.impl;

import com.wanted.preonboarding.config.security.JwtTokenProvider;
import com.wanted.preonboarding.data.board.Board;
import com.wanted.preonboarding.data.board.dto.BoardInfoDto;
import com.wanted.preonboarding.data.board.dto.BoardRequestDto;
import com.wanted.preonboarding.data.user.User;
import com.wanted.preonboarding.repository.BoardRepository;
import com.wanted.preonboarding.repository.UserRepository;
import com.wanted.preonboarding.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public BoardServiceImpl(UserRepository userRepository, BoardRepository boardRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void createBoard(String token, BoardRequestDto boardRequestDto) {
        String username = jwtTokenProvider.getUsername(token);
        User author = userRepository.getByEmail(username);
        Board board = Board.createBoard(author, boardRequestDto);
        boardRepository.save(board);
    }

    @Override
    public void updateBoard(String token, Long boardId, BoardRequestDto boardRequestDto) {

    }

    @Override
    public void deleteBoard(String token, Long boardId) {

    }

    @Override
    public BoardInfoDto getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다.")).toBoardInfoDto();
    }
}
