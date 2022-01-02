package com.roomy.service.impl;

import com.roomy.dto.BoardDTO;
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
    public List<BoardDTO> selectAllByUserId(String userId) {
        List<BoardVO> boardVOList
                = boardRepository.findAllByBoardCodeAndUserIdOrderByBoardSeqDesc(2,userId);
       // log.debug("보드리스트 {}", list);
       // return list;
return null;
    }

    @Override
    public BoardDTO findById(Long boardSeq) {
        BoardVO boardVO = (BoardVO) boardRepository.findById(boardSeq).orElse(null);
//        log.debug("findById 나와라 {}",boardVO.toString());
        //return boardVO;
        return  null;
    }

    @Override
    public Long saveBoard(BoardDTO board) {

        log.debug("board insert 메서드 {}", board.toString());
        board.setCreateDate(nowDateAndTime());
        BoardVO boardVO = board.toEntity().build();
        boardRepository.save(boardVO);

        return boardVO.getBoardSeq();
    }

    @Override
    public Long updateBoard(BoardDTO board) {

        board.setUpdateDate(nowDateAndTime());

        BoardVO boardVO = new BoardVO();
        boardRepository.save(boardVO);

        return boardVO.getBoardSeq();
    }

    @Override
    public void deleteBoard(Long boardSeq) {

        boardRepository.deleteById(boardSeq);
    }

    private String nowDateAndTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return dateTime;
    }

    // 모든 일반 게시물 조회
    @Override
    public List<BoardDTO> getBoardList() {
      boardRepository.findAllByBoardCode(2);

      return null;
    }

}
