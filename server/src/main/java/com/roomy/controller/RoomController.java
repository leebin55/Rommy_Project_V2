package com.roomy.controller;

import com.roomy.dto.RoomMainDTO;
import com.roomy.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/room")
@RestController @Slf4j
public class RoomController {

    private final RoomService roomService;


    @GetMapping("/{roomUser}")
    public ResponseEntity<?> loadRoomMainInfo(@PathVariable("roomUser") String roomUsername
    , Principal principal){
        String loggedInUsername = principal.getName();
        // front 에서 두 username이 같으면 버튼 안보이게 했지만 여기에서도 처리
        if(roomUsername != loggedInUsername){
            RoomMainDTO roomInfo = roomService.loadRoomMainInfo(roomUsername, loggedInUsername);
        }
    return null;
    }
}
