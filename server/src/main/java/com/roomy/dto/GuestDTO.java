package com.roomy.dto;

import com.roomy.model.GuestVO;
import com.roomy.model.othertype.GuestStatus;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @ToString
public class GuestDTO {

    private Long guestSeq;
    private Long roomSeq;
    private Long userId;
    private LocalDateTime date;
    private GuestStatus status;
    private  String content;

    // 날짜 빼고
    public GuestDTO(Long guestSeq, Long roomSeq, Long userId, GuestStatus status, String content) {
        this.guestSeq = guestSeq;
        this.roomSeq = roomSeq;
        this.userId = userId;
        this.status = status;
        this.content = content;
        this.date=LocalDateTime.now();
    }

    public GuestDTO(Long guestSeq, Long roomSeq, Long userId, LocalDateTime date, GuestStatus status, String content) {

        this.guestSeq = guestSeq;
        this.roomSeq = roomSeq;
        this.userId = userId;
        this.date = date;
        this.status = status;
        this.content = content;
    }
    public GuestVO toEntity(){
         return new GuestVO(guestSeq,userId,date,status,content);
    }
}