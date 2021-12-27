package com.roomy.model.board;

import com.roomy.model.LikeVO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//추상클래스 상속관계 매핑
@Entity
// 상속관계 매핑이기 때문에 부모클래스에 전략을 지정=> 일반board와 gallery  한 테이블에
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="btype")
@Getter
@Setter
@Table(name = "tbl_board", schema="roomyDB")
public abstract class Board {
    // 게시물 번호 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long boardSeq;

    // 작성한 회원 아이디 FK
    private String boardUserId;

    // 게시물 제목
    private String boardTitle;

    // 게시물 내용
    @Column(columnDefinition = "VARCHAR(4000)")
    private String boardContent;

    // 게시물 작성 시간
    private String boardCreateAt;

    // 게시물 수정 시간
    private String boardUpdateAt;

    // 게시물 공개 여부(전체 공개 / 비공개 / 나를 팔로우한 친구공개 )
    @Enumerated(EnumType.STRING)
    private BoardStatus status;


    /** 컬렉션을 필드에서 바로 초기화한 이유
     * -> 하이버네이트는 엔티티를 컬렉션을 한번 감싸서 하이버네이트가 제공하는 내장 컬렉션(class org.hibernate.collection.internal.PersistentBag)으로 변경
     * -> new 초기화 하지 않고 사용할때 new (class java.util.ArrayList)를 하면 하이버네이트가 관리할 수 없음*/
   @OneToMany(mappedBy = "board")
   private List<LikeVO> likeList = new ArrayList<>();


    // @DiscriminatorColumn(name="btype") 로
    // 게시물 구분코드 ( G 갤러리 / B 일반게시판 )
//    private int boardCode;

    // 조회수
    @ColumnDefault("0")
    private int boardHit=0;


}
