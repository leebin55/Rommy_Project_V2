package com.roomy.dto;

import com.roomy.model.Board;
import com.roomy.model.othertype.BoardStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Builder(toBuilder = true)
@ToString
public class BoardDTO {

    private Long boardSeq;
     private String username;
     private String nickname;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int likeCount;
    private BoardStatus status;
    private int boardCode;



    public Board createBoard(){
        return Board.builder().boardSeq(boardSeq)
                .title(title).content(content).createDate(LocalDateTime.now())
                .status(status).boardCode(2).build();
    }

    public Board updateBoard(){
        return Board.builder().boardSeq(boardSeq).title(title).content(content)
                .updateDate(LocalDateTime.now())
                .status(status).build();
    }
}
