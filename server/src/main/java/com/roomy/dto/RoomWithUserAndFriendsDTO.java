package com.roomy.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Builder
public class RoomWithUserAndFriendsDTO {

    private String profile;

    private String roomname;
    private Long total;
    private String intro;

    private List<String> followList;
    private List<String> followerList;
}
