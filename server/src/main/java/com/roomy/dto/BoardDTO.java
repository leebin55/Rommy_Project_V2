package com.roomy.dto;

import com.roomy.model.BoardVO;
import com.roomy.model.UserVO;
import com.roomy.model.othertype.BoardStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class BoardDTO {

    private Long boardSeq;
     private UserDTO userDTO;
    private String title;
    private String content;
    private String createDate;
    private String updateDate;
    private int likeCount;
    private BoardStatus status;
    private int boardCode;

     protected BoardDTO() {
    }

    public BoardDTO(Long boardSeq, UserDTO userDTO, String title, String content, String createDate, String updateDate, int likeCount, BoardStatus status) {
        this.boardSeq = boardSeq;
        this.userDTO = userDTO;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.likeCount = likeCount;
        this.status = status;
        this.boardCode = 2;
    }



    public BoardVO toEntity(){
        UserVO user = new UserVO(userDTO.getUsername(),userDTO.getNickname());
        BoardVO board = new BoardVO(boardSeq,user,title,content,createDate,updateDate,status,2);
        return board;

    }
}
