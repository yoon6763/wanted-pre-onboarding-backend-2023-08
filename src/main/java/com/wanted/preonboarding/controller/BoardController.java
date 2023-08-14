package com.wanted.preonboarding.controller;

import com.wanted.preonboarding.data.board.dto.BoardInfoDto;
import com.wanted.preonboarding.data.board.dto.BoardRequestDto;
import com.wanted.preonboarding.service.BoardService;
import io.jsonwebtoken.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/board-api")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public String createBoard(
            @RequestHeader String token,
            @RequestBody BoardRequestDto boardRequestDto) {

        log.info("[createBoard] 게시글을 생성합니다. token : {}", token);
        log.info("[createBoard] 게시글을 생성합니다. title : {}, content : {}", boardRequestDto.getTitle(), boardRequestDto.getContent());
        boardService.createBoard(token, boardRequestDto);
        return "success";
    }

    @GetMapping
    public ResponseEntity<BoardInfoDto> getBoard(
            @RequestParam(value = "boardId") Long boardId) {

        BoardInfoDto board = boardService.getBoard(boardId);
        log.info("[getBoard] 게시글을 조회합니다. boardId : {}", board.getId());
        log.info("[getBoard] 게시글을 조회합니다. boardTitle : {}", board.getTitle());
        log.info("[getBoard] 게시글을 조회합니다. boardContent : {}", board.getContent());
//        log.info("[getBoard] 게시글을 조회합니다. boardAuthor : {}", board.getUser().getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(board);
    }


}
