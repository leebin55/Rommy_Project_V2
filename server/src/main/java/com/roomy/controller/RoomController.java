package com.roomy.controller;

import com.roomy.dto.room.RoomProfileDTO;
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
@RequestMapping("/rooms")
@RestController @Slf4j
public class RoomController {

    private final RoomService roomService;

    // Room 의 왼쪽 layout 부분 : 프로필과 total 친구목록등이있는 곳
    @GetMapping("/{username}/{roomId}")
    public ResponseEntity<?> loadRoomLayout(@PathVariable("username") String roomUsername
    , @PathVariable("roomId") Long roomId,Principal principal){
        log.info("roomLayout");
        String loggedInUsername = principal.getName();
            RoomProfileDTO roomInfo = roomService.loadRoomLayoutInfo(roomUsername, loggedInUsername);
    return null;
    }

    //Room 의 메인페이지 : 최근 게시물 목록들과 최근 방명록4개 보여줌
    @GetMapping("/{username}/{roomId}/main_list")
    public ResponseEntity<?> loadRoomMain(@PathVariable("username") String roomUsername,@PathVariable("roomId") Long roomId){
        log.info("roomMain");
        return null;
    }

    //인기 미니홈피
    @GetMapping("/top")
    public ResponseEntity<?> popularRooms(){
        log.info("인기미니홈피");
        return ResponseEntity.ok(roomService.loadTop4());
    }
}
