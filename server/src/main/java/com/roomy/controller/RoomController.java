package com.roomy.controller;

import com.roomy.dto.RoomDTO;
import com.roomy.dto.room.RoomProfileDTO;
import com.roomy.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/rooms")
@RestController @Slf4j
public class RoomController {

    private final RoomService roomService;

    // Room 정보만 불러오기
    @GetMapping("/{username}/{roomId}")
    public ResponseEntity<?> loadRoomInfo(@PathVariable("username") String username
            , @PathVariable("roomId") Long roomId){
        log.info("room 설정");
        RoomDTO room = roomService.findByRoomId(roomId);
        return ResponseEntity.ok(room);
    }

    // Room 정보수정
    @PatchMapping("/{username}/{roomId}")
    public ResponseEntity<?> updateRoom(@PathVariable("username") String username
            , @PathVariable("roomId") Long roomId ,@RequestBody RoomDTO roomDTO, Principal principal){
        log.info("room 정보수정 roomId :{}, roomUser : {} , tokenUser : {} ", roomId,username,
                principal.getName());
        if(username.equals(principal.getName())){
            roomService.updateRoom(roomDTO);
           return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().body("해당 권한이 없습니다.");
    }

    // Room 의 왼쪽 layout 부분 : 프로필과 total 친구목록 등이있는 곳
    @GetMapping("/{username}/{roomId}/users-room")
    public ResponseEntity<?> loadRoomLayout(@PathVariable("username") String roomUsername
    , @PathVariable("roomId") Long roomId,Principal principal){
        log.info("roomLayout");
        String loggedInUsername = principal.getName();
            RoomProfileDTO roomInfo = roomService.loadRoomLayoutInfo(roomUsername, loggedInUsername);
    return null;
    }

    //Room 의 메인페이지 : 최근 게시물 목록들과 최근 방명록4개 보여줌
    @GetMapping("/{username}/{roomId}/boards-guests")
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
