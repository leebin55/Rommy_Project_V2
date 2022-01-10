package com.roomy.dto;

import com.roomy.model.Guest;
import com.roomy.model.othertype.GuestStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @ToString @Builder
public class GuestDTO {

    private Long guestSeq;
    private Long roomSeq;
    private String username;
    private LocalDateTime date;
    private GuestStatus status;
    private  String content;

    // 날짜 빼고
    public GuestDTO(Long guestSeq, Long roomSeq, String username, GuestStatus status, String content) {
        this.guestSeq = guestSeq;
        this.roomSeq = roomSeq;
        this.username = username;
        this.status = status;
        this.content = content;
        this.date=LocalDateTime.now();
    }

    public GuestDTO(Long guestSeq, Long roomSeq, String username, LocalDateTime date, GuestStatus status, String content) {

        this.guestSeq = guestSeq;
        this.roomSeq = roomSeq;
        this.username = username;
        this.date = date;
        this.status = status;
        this.content = content;
    }
    public Guest toEntity(){
         return new Guest(guestSeq,username,date,status,content);
    }
}
