package com.roomy.dto;

import com.roomy.model.BoardVO;
import com.roomy.model.othertype.BoardStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class BoardDTO {

    private Long boardSeq;
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

    public BoardDTO(Long boardSeq,String username,String nickname, String title, String content, String createDate, String updateDate, int likeCount, BoardStatus status) {
        this.boardSeq = boardSeq;
        this.username = username;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.likeCount = likeCount;
        this.status = status;
        this.boardCode = 2;
    }



    public BoardVO toEntity(){
        BoardVO board = new BoardVO(boardSeq,title,content,createDate,updateDate,status,2);
        return board;

    }
}
