package com.roomy.dto;

import lombok.*;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString
public class LoginDTO {

    private String username;
    private String password;

    @Builder
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
