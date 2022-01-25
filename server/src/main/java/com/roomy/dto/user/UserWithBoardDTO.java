package com.roomy.dto.user;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString
public class UserWithBoardDTO {

    private String profile;
    private String nickname;
    private String username;
    private String title;
    private String content;
    private String createDate;
    private Long boardHit;
    private Long likeCount;

    @QueryProjection
    public UserWithBoardDTO(String profile, String nickname, String username, String title, String content, LocalDateTime createDate, Long boardHit, Long likeCount) {
        this.profile = profile;
        this.nickname = nickname;
        this.username = username;
        this.title = title;
        this.content = content;
        this.createDate = String.valueOf(createDate).substring(0,10);
        this.boardHit = boardHit;
        this.likeCount = likeCount;
    }
}
