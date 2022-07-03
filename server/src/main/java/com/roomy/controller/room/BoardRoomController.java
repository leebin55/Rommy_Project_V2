package com.roomy.controller.room;


import com.roomy.dto.BoardDTO;
import com.roomy.dto.user.UserWithBoardDTO;
import com.roomy.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/rooms")
public class BoardRoomController {

    @Qualifier("boardService")
    private final BoardService boardService;

    @GetMapping("/{username}/{roomId}/boards")
    public ResponseEntity<?> list(@PathVariable("username")String username,@PathVariable("roomId") Long roomId, Pageable pageable) {
        Slice<BoardDTO> boardDTOS = boardService.loadBoardByRoom(roomId, pageable);
        return ResponseEntity.ok(boardDTOS);
    }

    @PostMapping("/{username}/{roomId}/boards")
    public ResponseEntity<?> register(@PathVariable("username")String username,
            @PathVariable("roomId")Long roomId, @RequestBody BoardDTO boardDTO,
                                      Principal principal) {
        if(username.equals(principal.getName())){
            BoardDTO board = boardDTO.toBuilder().username(principal.getName())
                    .roomId(roomId).build();
            Long boardSeq= boardService.saveBoard(board);
            return ResponseEntity.ok(boardSeq);
        }
        return ResponseEntity.badRequest().body("글을 등록할 권한이 없습니다.");
    }

    @GetMapping("/{username}/{roomId}/boards/{boardSeq}")
    public ResponseEntity<?> detail(@PathVariable("username")String username,@PathVariable("roomId")Long roomId,
                                    @PathVariable("boardSeq") Long boardSeq) {
        UserWithBoardDTO userWithBoardDTO =  boardService.getBoardBySeqAndPlusHit(boardSeq);
        return ResponseEntity.ok(userWithBoardDTO);
    }

    @PatchMapping("/{username}/{roomId}/boards")
    public ResponseEntity<?> update(@PathVariable("username")String username,@PathVariable("roomId") Long roomId
            ,@RequestBody BoardDTO boardDTO) {
        Long boardSeq =boardService.updateBoard(boardDTO);
        return ResponseEntity.ok(boardSeq);
    }

    @DeleteMapping("/{username}/{roomId}/boards/{boardSeq}")
    public ResponseEntity<?> delete(@PathVariable("username")String username,@PathVariable("roomId")Long roomId,
                                    @PathVariable("boardSeq") Long boardSeq) {
        boardService.deleteBoard(boardSeq);
        return ResponseEntity.ok("ok");
    }

}
