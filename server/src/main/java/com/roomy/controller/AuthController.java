package com.roomy.controller;

import com.roomy.config.JWT.TokenProvider;
import com.roomy.dto.user.LoginDTO;
import com.roomy.dto.user.TokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@RestController @Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // 로그인
    @PostMapping("/login")
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
        log.info("login 후 토킁 생성 : {}", token);
        //Header 에 토큰을 넣고
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION, "Bearer " + token);

        return new ResponseEntity<>(TokenDTO.tokenInfo(token),
                httpHeaders, HttpStatus.OK);
    }
// 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestBody TokenDTO TokenDto) {
       return null;
    }
}
