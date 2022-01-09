package com.roomy.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Builder
public class CheckFollowDTO {

        private Boolean checkFollow;
        private Boolean sameUser;

}
