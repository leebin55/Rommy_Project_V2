package com.roomy.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

//@Setter> 생성 시점에 값을 세팅할 예정
@Getter
//@ToString() > ToString 을 하게되면 연관관계 되어있는 것까지 다 끌고 오기 때문에 무한 루프가 발생할 수 있음
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="tbl_like" , schema = "roomyDB")
public class LikeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long likeSeq;

    // 좋아요 게시물 seq FK
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="board_seq")
    private BoardVO board;

    // 좋아요 누른 회원 Seq
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private UserVO user;

    public LikeVO(Long likeSeq, BoardVO board, UserVO user) {
        this.likeSeq = likeSeq;
        this.board = board;
        this.user = user;
    }
}
