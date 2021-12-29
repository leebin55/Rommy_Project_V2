package com.roomy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class GalleryDTO {


    private Long boardSeq;
    private String title;
    private String createDate;
    private int likeCount;
    // 이미지 한개만
    private String img;


}
