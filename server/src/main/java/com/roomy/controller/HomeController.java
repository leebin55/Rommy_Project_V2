package com.roomy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> home(){
        // 맨 처음 화면
        // 로그인 확인 후 로그인 되있으면 회원정보 리턴
        return null;
    }

    @GetMapping("/feeds")
    public ResponseEntity<?> feed(){
        // 로그인 전이면 공개 갤러리 게시물
        // 로그인 후면 친구 게시물
        log.info("feed 실행");
        return null;
    }
}
