package com.roomy.model;

import com.roomy.model.othertype.BoardStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
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

    // 메인페이지 피드에서 회원정보와 같이 보여주기 위해(단방향으로 설정)
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

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<BoardImage>  imgList = new ArrayList<>();

// likeCount 칼럼으로 넣을지 아님 likeList 에서 count 할지 고민중
  //  private Long likeCount;

    // 게시물 구분코드 ( 1 갤러리 / 2 일반게시판 )
     private int boardCode;

    // 조회수
    @ColumnDefault("0")
    private int boardHit=0;

    @Builder(toBuilder = true)
    public Board(Long boardSeq, Room room, User user, String title, String content, BoardStatus status, List<Like> likeList, List<Comment> commentList, List<BoardImage> imgList, int boardCode, int boardHit) {
        this.boardSeq = boardSeq;
        this.room = room;
        this.user = user;
        this.title = title;
        this.content = content;
        this.status = status;
        this.likeList = likeList;
        this.commentList = commentList;
        this.imgList = imgList;
        this.boardCode = boardCode;
        this.boardHit = boardHit;
    }

    // == 연관관계 method ==// 연관관계 주인 아닌 곳에서도 까먹지 않고 값을 입력해 주기 위해
    public void setRoom(Room room){
        this.room = room;
        room.getBoardList().add(this);
    }



}
