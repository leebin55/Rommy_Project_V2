package com.roomy.controller;

import com.roomy.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/follows")
@RestController @Slf4j
public class FollowController {

    private final FollowService followService;

    // 로그인한 유저가  username 을 follow  했는지 확인
    @GetMapping("/{username}")
    public ResponseEntity<?> checkFollow(@PathVariable("username") String username,Principal principal){
        Boolean checkFollow = followService.checkFollow(principal.getName(), username);
        return ResponseEntity.ok(checkFollow);
    }

}
