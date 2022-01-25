package com.roomy.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.roomy.entity.Guest;
import com.roomy.entity.othertype.GuestStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString @Builder(toBuilder = true)
public class GuestDTO {

    private Long guestSeq;
    private Long roomId;
    private  String nickname;
    private String username;
    private LocalDateTime crateDate;
    private GuestStatus status;
    private  String content;

    @QueryProjection
    public GuestDTO(Long guestSeq, String nickname, String username, LocalDateTime crateDate, GuestStatus status, String content) {
        this.guestSeq = guestSeq;
        this.nickname = nickname;
        this.username = username;
        this.crateDate = crateDate;
        this.status = status;
        this.content = content;
    }

    public Guest toEntity(){
      return
              Guest.builder().username(username).nickname(nickname).content(content).status(status).build();
    }
}
