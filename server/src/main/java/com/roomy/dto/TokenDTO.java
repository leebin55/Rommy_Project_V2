package com.roomy.dto;

import lombok.Getter;

@Getter
public class TokenDTO {

    private String access_token;

    public TokenDTO(String token) {
        this.access_token = token;
    }
}
