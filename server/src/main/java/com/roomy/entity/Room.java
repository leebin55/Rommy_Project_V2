package com.roomy.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
//@ToString(of={"roomSeq","roomname","intro","total"})
@Table(name="tbl_room", schema="roomyDB")
public class Room {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long roomId;

    // room 과 User 관계에서 Room 이 연관관계 주인 으로 설정 (외래키를 관리 할 곳)
    @OneToOne( fetch = LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="username") //fk 참조할 곳 지정
    private User user;

    // room 이름
    private String roomName;

    // room 방문자수
    @ColumnDefault("0")
    private Long total;

    // room 소개글
    private String intro;

    @Builder.Default
    @OneToMany(mappedBy = "room",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Board> boardList= new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "room",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Guest> guestList= new ArrayList<>();

    // Room을 방문할 때마다 total 만 1씩 증가하기때문에
    public void setPlusTotal(Long total){
        this.total= total+1;
    }

    public void updateRoom(String roomName, String intro){
        this.roomName = roomName;
        this.intro = intro;
    }
    public void setUser(User user){
        //UserVO user = new USerVO();
        // RoomVO room = new RoomVO();
        // user.setRoom(room);
        // room.setUser(user)
        this.user = user;
        user.setRoom(this);
    }
}
