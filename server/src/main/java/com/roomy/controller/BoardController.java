package com.roomy.controller;


import com.roomy.dto.BoardDTO;
import com.roomy.dto.user.UserWithBoardDTO;
import com.roomy.entity.Room;
import com.roomy.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/rooms")
public class BoardController {

    @Qualifier("boardService")
    private final BoardService boardService;

    // room 에서 글 조회
    @GetMapping("/{roomId}/boards")
    public ResponseEntity<?> list(@PathVariable("roomId") Room room, Pageable pageable) {
        log.debug("board list 컨트롤러 실행 {}", room);
        Slice<BoardDTO> boardDTOS = boardService.loadBoardByRoom(room, pageable);
//        return ResponseEntity.ok(boardList);
        return ResponseEntity.ok(boardDTOS);
    }

    // 글 등록
    @PostMapping("{username}/{roomId}/boards")
    public ResponseEntity<?> register(@PathVariable("username")String username,
            @PathVariable("roomId")Long roomId, @RequestBody BoardDTO boardDTO,
                                      Principal principal) {
        // 현재 로그인한 유저와 해당 룸의 유저가 같을 때만 글 들록
        if(username.equals(principal.getName())){
            BoardDTO board = boardDTO.toBuilder().username(principal.getName())
                    .roomId(roomId).build();
            log.debug("board write 컨트롤러 실행 {}", board.toString());
            Long boardSeq= boardService.saveBoard(board);
            return ResponseEntity.ok(boardSeq);
        }
        return ResponseEntity.badRequest().body("글을 등록할 권한이 없습니다.");
    }

    // 글 상세보기
    @GetMapping("/{roomId}/boards/{boardSeq}")
    public ResponseEntity<?> detail(@PathVariable("roomId")Long roomId,
                                    @PathVariable("boardSeq") Long boardSeq) {
        log.debug("board detail 컨트롤러 실행 {}",boardSeq);
        UserWithBoardDTO userWithBoardDTO =  boardService.getBoardBySeq(boardSeq);
        return ResponseEntity.ok(userWithBoardDTO);
    }

    // 글 수정
    @PatchMapping("/{username}/{roomId}/boards")
    public ResponseEntity<?> update(@RequestBody BoardDTO boardDTO) {
        log.debug("board update 컨트롤러 실행 {}", boardDTO.toString());
        Long boardSeq =boardService.updateBoard(boardDTO);
        return ResponseEntity.ok(boardSeq);
    }

    // 글 삭제
    @DeleteMapping("/{userId}/{roomId}/boards/{board_seq}")
    public void delete(@PathVariable Long board_seq) {
        log.debug("board delete 컨트롤러 실행");
        boardService.deleteBoard(board_seq);
    }

}
