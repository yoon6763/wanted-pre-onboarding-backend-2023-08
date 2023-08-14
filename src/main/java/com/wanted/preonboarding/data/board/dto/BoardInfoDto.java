package com.wanted.preonboarding.data.board.dto;

import com.wanted.preonboarding.data.user.dto.UserInfoDto;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardInfoDto {
    Long id;
    String title;
    String content;
    UserInfoDto user;
}
