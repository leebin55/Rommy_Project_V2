package com.roomy.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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


}
