package com.roomy.dto;

import com.roomy.entity.Board;
import com.roomy.entity.othertype.BoardStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Builder(toBuilder = true)
@ToString
public class BoardDTO {

    private Long boardSeq;
    private Long roomId;
    private String username;
    private String nickname;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private Long boardHit;
    private Long likeCount;
    private BoardStatus status;
    private int boardCode;



    public Board createBoard(){
        return Board.builder().boardSeq(boardSeq)
                .title(title).content(content)
                .status(status).boardCode(2)
                .likeCount(0L)
                .boardHit(0L).build();
    }

    public Board updateBoard(){
        return Board.builder().boardSeq(boardSeq).title(title).content(content)
                .status(status).build();
    }


}
