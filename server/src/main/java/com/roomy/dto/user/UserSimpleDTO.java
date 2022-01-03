package com.roomy.dto.user;

import lombok.Getter;

// 간단한 유저 조회(id, email, nickname만)
@Getter
public class UserSimpleDTO {
    private  String username;
    private String email;
    private String nickname;

    public UserSimpleDTO(String username, String email, String nickname) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
    }
}
