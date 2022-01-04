package com.roomy.dto;

import com.roomy.model.BoardVO;
import com.roomy.model.othertype.BoardStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class BoardDTO {

    private Long boardSeq;
    private Long userId;
    private String username;
    private String nickname;
    private String title;
    private String content;
    private String createDate;
    private String updateDate;
    private int likeCount;
    private BoardStatus status;
    private int boardCode;

     protected BoardDTO() {
    }

    public BoardDTO(Long boardSeq, Long userId, String nickname,String title, String content, String createDate, int likeCount,
                    BoardStatus status) {
        this.boardSeq = boardSeq;
        this.userId = userId;
        this.nickname= nickname;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.likeCount = likeCount;
        this.status = status;
        this.boardCode = 2;
    }

    public BoardVO toEntity(){
       // BoardVO board = new BoardVO(boardSeq,username,title,content,createDate,updateDate,status,2);
        //return board;
        return null;
    }
}
