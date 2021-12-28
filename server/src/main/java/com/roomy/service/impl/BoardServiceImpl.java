package com.roomy.service.impl;

import com.roomy.model.BoardVO;
import com.roomy.repository.BoardRepository;
import com.roomy.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service("boardService")
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @Override
    public List<BoardVO> search(String select, String query) {
        return null;
    }

    // userId 가 쓴 일반 게시물 조회
    @Override
    public List<BoardVO> selectAllByUserId(String userId) {
        List<BoardVO> list = boardRepository.findAllByBoardCodeAndUserIdOrderByBoardSeqDesc(2,userId);
        log.debug("보드리스트 {}", list);
        return list;

    }

    @Override
    public BoardVO findById(Long boardSeq) {
        BoardVO boardVO = (BoardVO) boardRepository.findById(boardSeq).orElse(null);
//        log.debug("findById 나와라 {}",boardVO.toString());
        return boardVO;
    }

    @Override
    public void insert(BoardVO boardVO) {

        log.debug("board insert 메서드 {}", boardVO.toString());

        boardVO.setCreateDate(nowDateAndTime());

        boardRepository.save(boardVO);
    }

    @Override
    public void update(BoardVO boardVO) {

        boardVO.setUpdateDate(nowDateAndTime());

        boardRepository.save(boardVO);
    }

    @Override
    public void delete(Long boardSeq) {
        boardRepository.deleteById(boardSeq);
    }

    private String nowDateAndTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return dateTime;
    }

    // 모든 일반 게시물 조회
    @Override
    public List<BoardVO> selectAll() {
        return boardRepository.findAllByBoardCode(2);
    }

}
