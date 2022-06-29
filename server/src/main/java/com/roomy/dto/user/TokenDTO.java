package com.roomy.dto.user;

import lombok.Getter;

import java.util.Map;

@Getter
public class TokenDTO {

    private String token;

    public static TokenDTO tokenInfo(String token){

        return new TokenDTO(token);
    }

    private TokenDTO(String token ) {
        this.token = token;

    }


}
