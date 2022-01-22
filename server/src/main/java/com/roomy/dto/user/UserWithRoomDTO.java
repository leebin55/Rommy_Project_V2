package com.roomy.dto.user;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class UserWithRoomDTO {

    private String username;
    private String email;
    private String profile;
    private String nickname;
    private Long roomSeq;
    private String intro;
    private String roomName;
    private Long total;

}
