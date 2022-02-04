package com.roomy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {



    @GetMapping("/feeds")
    public ResponseEntity<?> feed(){
        // 로그인 전이면 공개 갤러리 게시물
        // 로그인 후면 친구 게시물
        return null;
    }
}
