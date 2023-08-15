package com.wanted.preonboarding.exception.handler;

import com.wanted.preonboarding.entity.common.ErrorResponse;
import com.wanted.preonboarding.exception.board.BoardException;
import com.wanted.preonboarding.exception.user.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class BoardExceptionHandler {

    @ExceptionHandler(BoardException.class)
    public ResponseEntity<ErrorResponse> boardExceptionHandler(
            BoardException e, HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        messages.add(e.getBoardErrorCode().getMessage());
        log.error("Messages = {}", messages);

        ErrorResponse errorResponseDto = createErrorResponseDto(
                e.getBoardErrorCode().getHttpStatus(), messages, request);
        return ResponseEntity.status(errorResponseDto.getStatusCode()).body(errorResponseDto);
    }

    private ErrorResponse createErrorResponseDto(
            HttpStatus status, List<String> messages, HttpServletRequest request
    ) {
        String requestURI = request.getRequestURI();

        ErrorResponse errorResponseDto = ErrorResponse.builder()
                .statusCode(status.value())
                .errorType(status.name())
                .path(requestURI)
                .build();
        messages.forEach(errorResponseDto::addMessage);
        return errorResponseDto;
    }
}