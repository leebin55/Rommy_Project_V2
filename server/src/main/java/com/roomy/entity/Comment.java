package com.roomy.entity;

import com.roomy.entity.othertype.GuestStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name="tbl_comment")
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long commentSeq;

    // 댓글 쓴 게시물 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_seq")
    private Board board;

    private String username;

    // 댓글 단 회원 nickname
    private String nickname;

    // 공개, 비공개
    @Enumerated(EnumType.STRING)
    private GuestStatus status;

    // 댓글 내용
    private String content;



    public void setBoard(Board board){
        this.board = board;
        board.getCommentList().add(this);
    }
}
