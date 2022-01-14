package com.roomy.controller;

import com.roomy.dto.RoomWithUserAndFriendsDTO;
import com.roomy.service.RoomService;
import com.roomy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController @Slf4j
public class RoomController {

    private final RoomService roomService;


    @GetMapping("/{username}")
    public ResponseEntity<?> loadRoomMainInfo(@PathVariable("username") String username){
    RoomWithUserAndFriendsDTO roomInfo = roomService.loadRoomMainInfo(username);
    return null;
    }
}
