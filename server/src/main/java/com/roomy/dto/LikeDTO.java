package com.roomy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@ToString
public class LikeDTO {

    private String username;
    private Long boardSeq;
    private boolean likeCheck;
    private int likeNum;
}
