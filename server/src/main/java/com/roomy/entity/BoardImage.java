package com.roomy.entity;



import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name="tbl_board_image")
public class BoardImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long imgSeq;
    // 이미지 파일 url
    private String imgUrl;

    // board : imgs = 1 : N join
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="board_seq") // FK
    private Board board;

    public void setBoard(Board board){
        this.board = board;
        board.getImgList().add(this);
    }

}
