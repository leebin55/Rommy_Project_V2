package com.roomy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@ToString
public class LikeDTO {

    private Long userId;
    private Long boardSeq;
    private boolean likeCheck;
    private int likeNum;
}
