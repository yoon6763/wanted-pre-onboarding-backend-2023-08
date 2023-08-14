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

    @GetMapping(value = "/{boardId}")
    public ResponseEntity<BoardInfoDto> getBoard(@PathVariable Long boardId) {
        BoardInfoDto board = boardService.getBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(board);
    }

    @PutMapping(value = "/{boardId}")
    public String updateBoard(
            @RequestHeader String token,
            @PathVariable Long boardId,
            @RequestBody BoardRequestDto boardRequestDto) {

        boardService.updateBoard(token, boardId, boardRequestDto);
        return "success";
    }
}
