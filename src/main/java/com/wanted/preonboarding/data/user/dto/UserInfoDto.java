package com.wanted.preonboarding.data.user.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoDto {
    Long id;
    String email;
}
