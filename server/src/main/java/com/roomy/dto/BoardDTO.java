package com.roomy.dto;

import com.roomy.model.BoardVO;
import com.roomy.model.othertype.BoardStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class BoardDTO {

    private Long boardSeq;
    private String userId;
    private String title;
    private String content;
    private String createDate;
    private String updateDate;
    private int likeCount;
    private BoardStatus status;

    public BoardVO.BoardVOBuilder toEntity(){
        return BoardVO.builder().boardSeq(boardSeq)
                .boardCode(2)
                .userId(userId)
                .title(title)
                .content(content)
                .createDate(createDate)
                .updateDate(updateDate)
                .status(status);
    }
}
