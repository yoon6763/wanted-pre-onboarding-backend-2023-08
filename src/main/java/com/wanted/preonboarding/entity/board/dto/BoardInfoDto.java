package com.wanted.preonboarding.entity.board.dto;

import com.wanted.preonboarding.entity.user.dto.UserInfoDto;
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
