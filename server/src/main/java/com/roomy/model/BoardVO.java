package com.roomy.model;

import com.roomy.model.othertype.BoardStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_board", schema="roomyDB")
public class BoardVO{

    // 게시물 번호 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long boardSeq;

    /** room : board = 1 : N*/
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_seq")
    private RoomVO room;

    // 메인페이지 피드에서 회원정보와 같이 보여주기 위해(단방향으로 설정)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="username")
    private UserVO user;

    // 게시물 제목
    private String title;

    // 게시물 내용
    @Column(columnDefinition = "VARCHAR(4000)")
    private String content;

    // 게시물 작성 시간
    // LocalDateTime : hibernates 가 알아서 세팅
    private String createDate;

    // 게시물 수정 시간
    private String  updateDate;

    // 게시물 공개 여부(전체 공개 / 비공개 / 나를 팔로우한 친구공개 )
    @Enumerated(EnumType.STRING)
    private BoardStatus status;


    /** 컬렉션을 필드에서 바로 초기화한 이유
     * -> 하이버네이트는 엔티티를 컬렉션을 한번 감싸서 하이버네이트가 제공하는 내장 컬렉션(class org.hibernate.collection.internal.PersistentBag)으로 변경
     * -> new 초기화 하지 않고 사용할때 new (class java.util.ArrayList)를 하면 하이버네이트가 관리할 수 없음*/
    @OneToMany(mappedBy = "board", fetch = LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LikeVO> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CommentVO> commentList = new ArrayList<>();

    //BoardImg 테이블에 있는 board 필드에 의해 매칭
    @OneToMany(mappedBy = "board", fetch = LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<BoardImageVO>  imgList = new ArrayList<>();

// likeCount 칼럼으로 넣을지 아님 likeList 에서 count 할지 고민중
  //  private Long likeCount;

    // 게시물 구분코드 ( 1 갤러리 / 2 일반게시판 )
     private int boardCode;

    // 조회수
    @ColumnDefault("0")
    private int boardHit=0;


    // == 연관관계 method ==// 연관관계 주인 아닌 곳에서도 까먹지 않고 값을 입력해 주기 위해
    public void setRoom(RoomVO room){
        this.room = room;
        room.getBoardList().add(this);
    }


    public BoardVO(Long boardSeq, UserVO user, String title, String content, String createDate, String updateDate, BoardStatus status,int boardCode) {
        this.boardSeq = boardSeq;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
        this.boardCode = boardCode;
    }
}
