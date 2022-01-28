package com.roomy.controller;

import com.roomy.dto.GuestDTO;
import com.roomy.service.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/rooms")
@RestController @Slf4j
public class GuestController {

    private final GuestService guestService;

    //방명록 전체 조회
    @GetMapping("/{username}/{roomId}/guests")
    public ResponseEntity<?> list(@PathVariable("username")String roomUser
            , @PathVariable("roomId") Long roomId
            , Pageable pageable) {
//             guestService.getGuestByUsername(username);
        log.debug("guest list 컨트롤러 실행 pageable : {}", pageable.toString());

        Slice<GuestDTO> guestSlice = guestService.getAllGuestByRoom(roomId, pageable);
        return ResponseEntity.ok(guestSlice);
    }

    // 방명록 등록
    @PostMapping("{roomUser}/{roomId}/guests")
    public ResponseEntity<?> register(@PathVariable("roomUser")String roomUser,
            @PathVariable("roomId")Long roomId,@RequestBody GuestDTO guestDTO,
            Principal principal){
        log.info("방명록 등록 : {} , user : {}",guestDTO.toString(),principal.toString());
        Long guestSeq = guestService.saveGuest(guestDTO,principal.getName());
        return ResponseEntity.ok(guestSeq);
    }

    // 최근 방명록 4개만
    @GetMapping("/{username}/{roomId}/guest_recent")
    public ResponseEntity<?> recentList(@PathVariable("username")String roomUser,@PathVariable("roomId") Long roomId){
        log.info("최근 방명록 4 개만 불러오기");
        return ResponseEntity.ok(guestService.getRecentGuest(roomId));
    }
}
