package com.roomy.controller;

import com.roomy.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/rooms")
@RestController @Slf4j
public class FollowRoomController {

    private final FollowService followService;

    @GetMapping("/{username}/{roomId}/followings")
    public ResponseEntity<?> loadFollowings(@PathVariable("username") String username){
        return ResponseEntity.ok("");
    }

    @GetMapping("/{username}/{roomId}/followers")
    public ResponseEntity<?> loadFollowers(@PathVariable("username") String username){

        return ResponseEntity.ok("");
    }

}
