package com.roomy.dto;

import lombok.Getter;

@Getter
public class FriendDTO {

    // 로그인한 유저 : 주체
    private String loggedInUser;
    private String roomUser;// 해당 미니홈페이지의 주인
}
