package com.wanted.preonboarding.data.board;

import com.wanted.preonboarding.data.board.dto.BoardInfoDto;
import com.wanted.preonboarding.data.board.dto.BoardRequestDto;
import com.wanted.preonboarding.data.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Board createBoard(User user, BoardRequestDto boardRequestDto) {
        return Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .user(user)
                .build();
    }

    public BoardInfoDto toBoardInfoDto() {
        return BoardInfoDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .user(user.toUserInfoDto())
                .build();
    }
}
