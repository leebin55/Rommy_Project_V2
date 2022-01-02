package com.roomy.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="tbl_room", schema="roomyDB")
public class RoomVO {

    // 각 회원당 room 은 하나씩만 가질 수 있기때문에
    // PK 는 회원번호
    @Id
    private Long roomSeq;

    @OneToOne(mappedBy = "room", fetch = FetchType.LAZY)
    private UserVO user;

    // room 이름
    private String roomName;

    // room 방문자수
    @ColumnDefault("0")
    private int roomTotal;

    // room 소개글
    private String roomIntroduce;

    @OneToMany(mappedBy = "room")
    private List<BoardVO> boardList= new ArrayList<>();

}
