package com.roomy.model;

import com.roomy.model.othertype.BoardStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_board", schema="roomyDB")
public class BoardVO{

    // 게시물 번호 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long boardSeq;

    /** room : board = 1 : 1*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_seq")
    private RoomVO room;

    // 작성한 회원 아이디 FK
    private String userId;



    // 게시물 제목
    private String title;

    // 게시물 내용
    @Column(columnDefinition = "VARCHAR(4000)")
    private String content;

    // 게시물 작성 시간
    private String createDate;

    // 게시물 수정 시간
    private String  updateDate;

    // 게시물 공개 여부(전체 공개 / 비공개 / 나를 팔로우한 친구공개 )
    @Enumerated(EnumType.STRING)
    private BoardStatus status;


    /** 컬렉션을 필드에서 바로 초기화한 이유
     * -> 하이버네이트는 엔티티를 컬렉션을 한번 감싸서 하이버네이트가 제공하는 내장 컬렉션(class org.hibernate.collection.internal.PersistentBag)으로 변경
     * -> new 초기화 하지 않고 사용할때 new (class java.util.ArrayList)를 하면 하이버네이트가 관리할 수 없음*/
    @OneToMany(mappedBy = "board")
    private List<LikeVO> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<CommentVO> commentList = new ArrayList<>();

    //BoardImg 테이블에 있는 board 필드에 의해 매칭
    @OneToMany(mappedBy = "board")
    private List<BoardImageVO>  img = new ArrayList<>();


    // 게시물 구분코드 ( 1 갤러리 / 2 일반게시판 )
     private int boardCode;

    // 조회수
    @ColumnDefault("0")
    private int boardHit=0;

}
