package com.wanted.preonboarding.entity.common;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int statusCode;
    private String errorType;
    private final List<String> messages = new ArrayList<>();
    private String path;

    @Builder
    public ErrorResponse(int statusCode, String errorType, String path) {
        this.statusCode = statusCode;
        this.errorType = errorType;
        this.path = path;
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
