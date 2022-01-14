package com.roomy.dto.user;

import lombok.*;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString
public class LoginDTO {

    private String username;
    private String password;

    public static LoginDTO createLoginDTO(String username, String password){
        return new LoginDTO(username,password);
    }

    private LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
