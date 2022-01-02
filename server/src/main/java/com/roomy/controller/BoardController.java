package com.roomy.controller;


import com.roomy.dto.BoardDTO;
import com.roomy.model.BoardVO;
import com.roomy.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/room")
public class BoardController {

    @Qualifier("boardService")
    private final BoardService boardService;


    public BoardController(BoardService boardService ){
        this.boardService = boardService;

    }

    // 글 조회
    @GetMapping("/{userId}/board")
    public List<BoardDTO> list(@PathVariable String userId) {
        log.debug("board list 컨트롤러 실행 {}", userId);
        /**
         * 주의 >  BoardVO 자체를 리턴하면 여러 쿼리 실행 : 연관관계 때문에
         */
        List<BoardDTO> boardList = boardService.selectAllByUserId(userId);
        log.debug(boardList.toString());
        return boardList;
    }

    // 글 등록
    @PostMapping("/{userId}/board")
    public ResponseEntity<?> insert( @PathVariable String userId, @RequestBody BoardDTO boardDTO) {
        log.debug("board write 컨트롤러 실행 {}", boardDTO.toString());
       Long boardSeq= boardService.saveBoard(boardDTO);
        return ResponseEntity.ok(boardSeq);
    }

    // 글 상세보기
    @GetMapping("/{userId}/board/{board_seq}")
    public ResponseEntity<?> detail(@PathVariable Long board_seq) {
        log.debug("board detail 컨트롤러 실행 {}",board_seq);
        BoardDTO boardDTO =  boardService.findById(board_seq);

        return ResponseEntity.ok(boardDTO);
    }

    // 글 수정
    @PutMapping("/{userId}/board")
    public void update(@RequestBody BoardDTO boardDTO) {
        log.debug("board update 컨트롤러 실행 {}", boardDTO.toString());
        boardService.updateBoard(boardDTO);
    }

    // 글 삭제
    @DeleteMapping("/{userId}/board/{board_seq}")
    public void delete(@PathVariable Long board_seq) {
        log.debug("board delete 컨트롤러 실행");
        boardService.deleteBoard(board_seq);
    }

}
