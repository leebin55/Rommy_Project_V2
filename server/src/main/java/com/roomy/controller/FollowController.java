package com.roomy.controller;

import com.roomy.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/follows")
@RestController @Slf4j
public class FollowController {

    private final FollowService followService;

    @GetMapping("/{username}")
    public ResponseEntity<?> checkFollow(@PathVariable("username") String username,Principal principal){
        Boolean checkFollow = followService.checkFollow(principal.getName(), username);
        return ResponseEntity.ok(checkFollow);
    }

    @PostMapping("/{roomUser}")
    public ResponseEntity<?> follow(@PathVariable String roomUser, Principal principal){
        followService.follow(principal.getName(),roomUser);
    return ResponseEntity.ok("success");
    }

    @DeleteMapping("/{roomUser}")
    public ResponseEntity unFollow(@PathVariable String roomUser,Principal principal){
        followService.unfollow(principal.getName(), roomUser);
        return ResponseEntity.ok("success");
    }
}
