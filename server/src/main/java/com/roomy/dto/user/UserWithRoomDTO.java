package com.roomy.dto.user;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class UserWithRoomDTO {

    private String username;
    private String email;
    private String profile;
    private String nickname;
    private Long roomId;
    private String intro;
    private String roomName;
    private Long total;


    @QueryProjection
    public UserWithRoomDTO(String username, String email, String profile, String nickname, Long roomId, String intro, String roomName, Long total) {
        this.username = username;
        this.email = email;
        this.profile = profile;
        this.nickname = nickname;
        this.roomId = roomId;
        this.intro = intro;
        this.roomName = roomName;
        this.total = total;
    }

    @QueryProjection
    public UserWithRoomDTO(String username, String profile, String nickname, Long roomId) {
        this.username = username;
        this.profile = profile;
        this.nickname = nickname;
        this.roomId = roomId;
    }
}
