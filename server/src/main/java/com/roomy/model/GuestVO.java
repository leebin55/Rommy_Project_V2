package com.roomy.model;

import com.roomy.model.othertype.GuestStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_seq")
    private RoomVO room;

    // 글쓴 user
   @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private Long userId;


    @Column(columnDefinition = "VARCHAR(20)",nullable = false)
    private String date;

    @Column(columnDefinition = "VARCHAR(7)", nullable = false)
    @Enumerated(EnumType.STRING)
    private GuestStatus status;

    @Column(columnDefinition = "VARCHAR(4000)", nullable = false)
    private String content;




}
