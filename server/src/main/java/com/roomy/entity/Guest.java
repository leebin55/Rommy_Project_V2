package com.roomy.entity;

import com.roomy.entity.othertype.GuestStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name="tbl_guest", schema="roomyDB")
public class Guest extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestSeq;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "room_seq")
    private Room room;

    // 글쓴 user
    private String username;

    private String nickname;

    @Column(columnDefinition = "VARCHAR(7)", nullable = false)
    @Enumerated(EnumType.STRING)
    private GuestStatus status;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String content;

// == 연관관계 메서드
    public void setRoom(Room room){
        this.room = room;
        room.getGuestList().add(this);
    }

}
