package com.roomy.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter@ToString
public class LikeDTO {

    private String username;
    private Long boardSeq;
    private boolean likeCheck;
    private int likeNum;
}
