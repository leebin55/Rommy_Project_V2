package com.roomy.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
//@ToString(of={"roomSeq","roomname","intro","total"})
@Table(name="tbl_room", schema="roomyDB")
public class RoomVO {


    @Id
    private Long roomSeq;

    @OneToOne(mappedBy = "room", fetch = FetchType.LAZY)
    private UserVO user;

    // room 이름
    private String roomname;

    // room 방문자수
    @ColumnDefault("0")
    private int total;

    // room 소개글
    private String intro;

    @OneToMany(mappedBy = "room")
    private List<BoardVO> boardList= new ArrayList<>();

    protected RoomVO() {
    }
}
