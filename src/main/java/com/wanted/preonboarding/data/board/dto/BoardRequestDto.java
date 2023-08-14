package com.wanted.preonboarding.data.board.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
    String title;
    String content;
}
