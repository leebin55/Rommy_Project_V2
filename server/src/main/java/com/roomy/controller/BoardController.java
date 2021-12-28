package com.roomy.controller;

import com.roomy.dto.SessionDTO;
import com.roomy.model.BoardVO;
import com.roomy.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public List<BoardVO> list(@PathVariable String userId) {
        log.debug("board list 컨트롤러 실행 {}", userId);
        List<BoardVO> boardList = boardService.selectAllByUserId(userId);
        log.debug(boardList.toString());
        return boardList;
    }

    // 글 등록
    @PostMapping("/{userId}/board")
    public String insert(HttpSession session, @PathVariable String userId, @RequestBody BoardVO boardVO) {
        log.debug("board write 컨트롤러 실행 {}", boardVO.toString());

        boardService.insert(boardVO);
        return "OK";
    }

    // 글 상세보기
    @GetMapping("/{userId}/board/{board_seq}")
    public BoardVO detail(@PathVariable Long board_seq, HttpSession session) {
        log.debug("board detail 컨트롤러 실행 {}",board_seq);
        BoardVO boardVO =  boardService.findById(board_seq);

        return boardVO;
    }

    // 글 수정
    @PutMapping("/{userId}/board")
    public void update(@RequestBody BoardVO boardVO) {
        log.debug("board update 컨트롤러 실행 {}", boardVO.toString());
        boardService.update(boardVO);
    }

    // 글 삭제
    @DeleteMapping("/{userId}/board/{board_seq}")
    public void delete(@PathVariable Long board_seq) {
        log.debug("board delete 컨트롤러 실행");
        boardService.delete(board_seq);
    }

}
