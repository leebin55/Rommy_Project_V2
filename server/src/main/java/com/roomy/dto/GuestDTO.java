package com.roomy.dto;

import com.roomy.model.Guest;
import com.roomy.model.othertype.GuestStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString @Builder
public class GuestDTO {

    private Long guestSeq;
    private Long roomSeq;
    private String username;
    private LocalDateTime crateDate;
    private GuestStatus status;
    private  String content;


    public Guest toEntity(){
      return   Guest.builder().username(username).content(content).status(status).build();
    }
}
