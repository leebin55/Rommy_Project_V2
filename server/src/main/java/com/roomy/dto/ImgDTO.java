package com.roomy.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ImgDTO {
    private Long imgSeq;
    private String imgUrl;
}
