package com.wanted.preonboarding.service.impl;

import com.wanted.preonboarding.config.security.JwtTokenProvider;
import com.wanted.preonboarding.entity.board.Board;
import com.wanted.preonboarding.entity.board.dto.BoardInfoDto;
import com.wanted.preonboarding.entity.board.dto.BoardRequestDto;
import com.wanted.preonboarding.entity.user.User;
import com.wanted.preonboarding.exception.user.impl.UserNotFoundException;
import com.wanted.preonboarding.repository.BoardRepository;
import com.wanted.preonboarding.repository.UserRepository;
import com.wanted.preonboarding.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        User author = userRepository.getByEmail(username).orElseThrow(UserNotFoundException::new);
        Board board = Board.createBoard(author, boardRequestDto);
        boardRepository.save(board);
    }

    @Override
    public void updateBoard(String token, Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
        User user = userRepository.getByEmail(jwtTokenProvider.getUsername(token)).orElseThrow(UserNotFoundException::new);

        if (!board.getUser().getEmail().equals(user.getEmail())) {
            throw new RuntimeException("게시글 작성자가 아닙니다.");
        }

        board.update(boardRequestDto);
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(String token, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
        boardRepository.delete(board);
    }

    @Override
    public BoardInfoDto getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다.")).toBoardInfoDto();
    }

    @Override
    public Page<BoardInfoDto> getPosts(Pageable pageable) {
        return boardRepository.findAll(pageable).map(Board::toBoardInfoDto);
    }
}
