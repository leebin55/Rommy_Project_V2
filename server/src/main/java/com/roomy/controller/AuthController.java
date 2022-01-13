package com.roomy.controller;

import com.roomy.config.JWT.TokenProvider;
import com.roomy.dto.LoginDTO;
import com.roomy.dto.TokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController @Slf4j
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    //    // 로그인
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        log.debug("login dto {} ",loginDTO.toString());
        // 아직 인증되기 전
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());
        // 위의 토큰을 이용해서 Authentication 객체를 생성하기위해 authenticate  method가 실행될때
        // loadUserByUsername실행
        log.info("authenticate method 실행 ");
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authToken);
        log.info("로그인 성공 => security context holder 에 authentication 담기 ");
        // 위의 authentication 을 securitycontext 에 담음
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 그 인중 정보를 이용해서 jwt 토큰 생성
        String token = tokenProvider.createToken(authentication);
        //Header 에 토큰을 넣고
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION, "Bearer " + token);

        return new ResponseEntity<>(new TokenDTO(token),httpHeaders, HttpStatus.OK);
    }

}
