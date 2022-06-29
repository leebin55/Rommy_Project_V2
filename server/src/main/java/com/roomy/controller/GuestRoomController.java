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

@RequiredArgsConstructor
@RequestMapping("/rooms")
@RestController @Slf4j
public class GuestRoomController {

    private final GuestService guestService;

    @GetMapping("/{username}/{roomId}/guests")
    public ResponseEntity<?> list(@PathVariable("username")String roomUser
            , @PathVariable("roomId") Long roomId
            , Pageable pageable) {
        Slice<GuestDTO> guestSlice = guestService.getAllGuestByRoom(roomId, pageable);
        return ResponseEntity.ok(guestSlice);
    }

    @GetMapping("/{username}/{roomId}/guests/{guestSeq}")
    public ResponseEntity<?> detail(@PathVariable("username")String roomUser
            , @PathVariable("roomId") Long roomId, @PathVariable("guestSeq")Long guestSeq) {
        return ResponseEntity.ok( guestService.findByGuestSeq(guestSeq));
    }

    @PostMapping("{username}/{roomId}/guests")
    public ResponseEntity<?> register(@PathVariable("username")String roomUser,
            @PathVariable("roomId")Long roomId,@RequestBody GuestDTO guestDTO,
            Principal principal){
        Long guestSeq = guestService.saveGuest(guestDTO,principal.getName());
        return ResponseEntity.ok(guestSeq);
    }

    // 최근 방명록 4개만
    @GetMapping("/{username}/{roomId}/guests-recent")
    public ResponseEntity<?> recentList(@PathVariable("username")String roomUser,@PathVariable("roomId") Long roomId){
        return ResponseEntity.ok(guestService.getRecentGuest(roomId));
    }

    @DeleteMapping("/{username}/{roomId}/guests/{guestSeq}")
    public ResponseEntity<?> deleteGuest(@PathVariable("username")String roomUser,@PathVariable("roomId") Long roomId,
                                         @PathVariable("guestSeq")Long guestSeq,Principal principal){
        if(principal.getName().equals(roomUser)){
            guestService.deleteGuest(guestSeq);
            return ResponseEntity.ok(guestSeq);
        }
        return ResponseEntity.badRequest().body("권한없음");
    }

    @PatchMapping("/{username}/{roomId}/guests")
    public ResponseEntity<?> updateGuest(@PathVariable("username")String roomUser,@PathVariable("roomId") Long roomId,
                                        @RequestBody GuestDTO guestDTO,Principal principal){
        if(principal.getName().equals(roomUser)){
            guestService.updateGuest(guestDTO);
            return ResponseEntity.ok(guestDTO.getGuestSeq());
        }
        return ResponseEntity.badRequest().body("권한없음");
    }
}
