package com.wanted.preonboarding.exception.handler;

import com.wanted.preonboarding.entity.common.ErrorResponse;
import com.wanted.preonboarding.exception.user.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> validationExceptionHandler(
            ConstraintViolationException e, HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();

        e.getConstraintViolations().forEach(constraintViolation -> {messages.add(constraintViolation.getMessage());});
        ErrorResponse errorResponseDto = createErrorResponseDto(HttpStatus.BAD_REQUEST, messages, request);

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