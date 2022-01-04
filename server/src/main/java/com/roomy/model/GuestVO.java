package com.roomy.model;

import com.roomy.model.othertype.GuestStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
@Table(name="tbl_guest", schema="roomyDB")
public class GuestVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestSeq;

    // 해당 Room
//    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room_seq")
    private RoomVO room;

    // 글쓴 user
   @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private Long userId;


    @Column(columnDefinition = "VARCHAR(20)",nullable = false)
    private LocalDateTime date;

    @Column(columnDefinition = "VARCHAR(7)", nullable = false)
    @Enumerated(EnumType.STRING)
    private GuestStatus status;

    @Column(columnDefinition = "VARCHAR(4000)", nullable = false)
    private String content;

// == 연관관계 메서드
    public void setRoom(RoomVO room){
        this.room = room;
        room.getGuestList().add(this);
    }

    public GuestVO(Long guestSeq, Long userId, LocalDateTime date, GuestStatus status, String content) {
        this.guestSeq = guestSeq;
        this.userId = userId;
        this.date = date;
        this.status = status;
        this.content = content;
    }
}
