package com.roomy.entity;

import com.roomy.entity.othertype.BoardCode;
import com.roomy.entity.othertype.BoardStatus;
import lombok.*;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long boardSeq;

    /** room : board = 1 : N*/
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="username")
    private User user;

    private String title;

    @Column(columnDefinition = "VARCHAR(4000)")
    private String content;
    
    // 게시물 공개 여부(전체 공개 / 비공개 / 나를 팔로우한 친구공개 )
    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "board",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Like> likeList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Image>  imgList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
     private BoardCode boardCode;

    private Long boardHit;

    public Long likeCount;

    public void setRoomAndUser(Room room,User user){
        this.room = room;
        this.user= user;
        user.getBoardList().add(this);
        room.getBoardList().add(this);
    }



}
