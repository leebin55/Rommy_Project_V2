package com.roomy.dto;

import lombok.*;
import org.springframework.data.domain.Slice;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Builder
public class RoomMainDTO {

    private String profile;

    private String roomname;
    private Long total;
    private String intro;

    private Boolean checkFollow;
    private Slice<String> followList;
    private Slice<String> followerList;
}
