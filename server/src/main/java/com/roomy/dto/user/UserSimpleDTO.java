package com.roomy.dto.user;

import lombok.Getter;

// 간단한 유저 조회(id, email, nickname만)
@Getter
public class UserSimpleDTO {
    private Long userId;
    private  String username;
    private String email;
    private String nickname;

    public UserSimpleDTO(Long userId, String username, String email, String nickname) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
    }
}
