package com.roomy.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
//@ToString(of={"roomSeq","roomname","intro","total"})
@Table(name="tbl_room", schema="roomyDB")
public class Room {


    @Id @GeneratedValue(strategy = IDENTITY)
    private Long roomSeq;

    // room 과 User 관계에서 Room 이 연관관계 주인 으로 설정 (외래키를 관리 할 곳)
    @OneToOne( fetch = LAZY)
    @JoinColumn(name="username") //fk 참조할 곳 지정
    private User user;

    // room 이름
    private String roomname;

    // room 방문자수
    @ColumnDefault("0")
    private Long total;

    // room 소개글
    private String intro;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Board> boardList= new ArrayList<>();

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Guest> guestList= new ArrayList<>();


    // Room을 방문할 때마다 total 만 1씩 증가하기때문에
    public void setTotal(Long total){
        this.total= total;
    }

    // == 생성자 ==//
    // roomSeq 도 같이 생성?????
    public Room(String roomname, String intro) {
        this.roomname = roomname;
        this.intro = intro;
    }


    // 두개를 원자적으로 묶음
    //== 연관관계 메서드 ==// : 컨트롤 하는 쪽이 연관관계 메서드를 가지고 있는 것이 좋음
    public void setUser(User user){
        //UserVO user = new USerVO();
        // RoomVO room = new RoomVO();
        // user.setRoom(room);
        // room.setUser(user)
        this.user = user;
        user.builder().room(this).build();
    }




}