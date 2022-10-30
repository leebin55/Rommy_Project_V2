package com.roomy.controller;

import com.roomy.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class HomeController {

    private final RoomService roomService;

    @GetMapping("/feeds")
    public ResponseEntity<?> feed(){
        return null;
    }

    //인기 미니홈피
    @GetMapping("/top-rooms")
    public ResponseEntity<?> popularRooms(){
        return ResponseEntity.ok(roomService.loadTop4());
    }
}
