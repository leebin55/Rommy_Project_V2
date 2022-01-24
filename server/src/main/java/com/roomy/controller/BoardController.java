package com.roomy.controller;


import com.roomy.dto.BoardDTO;
import com.roomy.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/rooms")
public class BoardController {

    @Qualifier("boardService")
    private final BoardService boardService;

    // 글 조회
    @GetMapping("/{username}/{roomId}/board")
    public ResponseEntity<?> list(@PathVariable("username") String username,
                                  @PathVariable("roomId")Long roomSeq) {
        log.debug("board list 컨트롤러 실행 {}", username);
        Page<BoardDTO> boardList = boardService.selectAllByUsername(username);
        return ResponseEntity.ok(boardList);
    }

    // 글 등록
    @PostMapping("/{username}/{roomId}/board")
    public ResponseEntity<?> register(@PathVariable("username")String username,
                                      @RequestBody BoardDTO boardDTO) {
        log.debug("board write 컨트롤러 실행 {}", boardDTO.toString());
       Long boardSeq= boardService.saveBoard(boardDTO);
        return ResponseEntity.ok(boardSeq);
    }

    // 글 상세보기
    @GetMapping("/{username}/{roomId}/{boardSeq}")
    public ResponseEntity<?> detail(@PathVariable("username") String username,
                                    @PathVariable("roomId")Long roomSeq,
                                    @PathVariable("boardSeq") Long board_seq) {
        log.debug("board detail 컨트롤러 실행 {}",board_seq);
        BoardDTO boardDTO =  boardService.getBoardBySeq(board_seq);

        return ResponseEntity.ok(boardDTO);
    }

    // 글 수정
    @PatchMapping("/{username}/{roomId}/board")
    public ResponseEntity<?> update(@RequestBody BoardDTO boardDTO) {
        log.debug("board update 컨트롤러 실행 {}", boardDTO.toString());
        Long boardSeq =boardService.updateBoard(boardDTO);
        return ResponseEntity.ok(boardSeq);
    }

    // 글 삭제
    @DeleteMapping("/{userId}/{roomId}/board/{board_seq}")
    public void delete(@PathVariable Long board_seq) {
        log.debug("board delete 컨트롤러 실행");
        boardService.deleteBoard(board_seq);
    }

}
