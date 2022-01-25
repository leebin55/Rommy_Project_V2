package com.roomy.entity;

import com.roomy.entity.othertype.BoardStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_board", schema="roomyDB")
public class Board extends BaseEntity{


    // 게시물 번호 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long boardSeq;

    /** room : board = 1 : N*/
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_seq")
    private Room room;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="username")
    private User user;

    // 게시물 제목
    private String title;

    // 게시물 내용
    @Column(columnDefinition = "VARCHAR(4000)")
    private String content;
    
    // 게시물 공개 여부(전체 공개 / 비공개 / 나를 팔로우한 친구공개 )
    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Like> likeList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<BoardImage>  imgList = new ArrayList<>();

// likeCount 칼럼으로 넣을지 아님 likeList 에서 count 할지 고민중
  //  private Long likeCount;

    // 게시물 구분코드 ( 1 갤러리 / 2 일반게시판 )
     private int boardCode;

    // 조회수
    private Long boardHit;

    public Long likeCount;

    // == 연관관계 method ==// 연관관계 주인 아닌 곳에서도 까먹지 않고 값을 입력해 주기 위해
    public void setRoomAndUser(Room room,User user){
        this.room = room;
        this.user= user;
        user.getBoardList().add(this);
        room.getBoardList().add(this);
    }



}
