package com.roomy.model;

import com.roomy.model.othertype.GuestStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name="tbl_guest", schema="roomyDB")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestSeq;

    // 해당 Room
//    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room_seq")
    private Room room;

    // 글쓴 user
   @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String username;


    @Column(columnDefinition = "VARCHAR(20)",nullable = false)
    private LocalDateTime date;

    @Column(columnDefinition = "VARCHAR(7)", nullable = false)
    @Enumerated(EnumType.STRING)
    private GuestStatus status;

    @Column(columnDefinition = "VARCHAR(4000)", nullable = false)
    private String content;

// == 연관관계 메서드
    public void setRoom(Room room){
        this.room = room;
        room.getGuestList().add(this);
    }

    public Guest(Long guestSeq, String username, LocalDateTime date, GuestStatus status, String content) {
        this.guestSeq = guestSeq;
        this.username = username;
        this.date = date;
        this.status = status;
        this.content = content;
    }
}
