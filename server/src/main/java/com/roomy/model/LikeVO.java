package com.roomy.model;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

//@Setter> 생성 시점에 값을 세팅할 예정
@Getter
//@ToString() > ToString 을 하게되면 연관관계 되어있는 것까지 다 끌고 오기 때문에 무한 루프가 발생할 수 있음
@Entity
@Table(name="tbl_like" , schema = "roomyDB")
public class LikeVO { // user 와 board 에대해 외래키 관리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long likeSeq;

    // 좋아요 게시물 seq FK
    @ManyToOne(fetch = LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="board_seq")
    private BoardVO board;

    // 좋아요 누른 회원 Seq
    @ManyToOne(fetch = LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="username")
    private UserVO user;

    private LocalDateTime date;

    public LikeVO() {
        this.date = LocalDateTime.now();
    }

    public LikeVO(BoardVO board, UserVO user) {
        this.board = board;
        this.user = user;
        this.date = LocalDateTime.now();
    }

    //== 연관관계 method
    public void setUser(UserVO user){
        this.user = user;
        user.getLikeList().add(this);
    }

    public void setBoard(BoardVO board){
        this.board= board;
        board.getLikeList().add(this);
    }
}
