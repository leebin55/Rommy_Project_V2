package com.roomy.model;

import com.roomy.model.othertype.GuestStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_comment")
public class CommentVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long commentSeq;

    // 댓글 쓴 게시물 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_seq")
    private BoardVO board;

    // 댓글 단 날짜 시간

    private LocalDateTime date;

    // 댓글 단 회원 아이디 > 댓글에서 User 엔티티를 참조할 필요가 없을거 같아서
    // 단반향으로 설정
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private String userId;
    
    // 댓글 단 회원 nickname
    private String nickname;

    // 공개, 비공개
    @Enumerated(EnumType.STRING)
    private GuestStatus status;

    // 댓글 내용
    private String content;



    public void setBoard(BoardVO board){
        this.board = board;
        board.getCommentList().add(this);
    }
}
