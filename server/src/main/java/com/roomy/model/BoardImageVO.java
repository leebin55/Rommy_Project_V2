package com.roomy.model;



import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Setter
@Getter
@ToString
@Entity
@Table(name="tbl_board_image")
public class BoardImageVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long imgSeq;
    // 이미지 파일 url
    private String imgUrl;

    // board : imgs = 1 : N join
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="board_seq") // FK
    private BoardVO board;

}
