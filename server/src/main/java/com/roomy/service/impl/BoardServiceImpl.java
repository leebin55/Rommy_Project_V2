package com.roomy.service.impl;

import com.roomy.dto.BoardDTO;
import com.roomy.model.BoardVO;
import com.roomy.repository.BoardRepository;
import com.roomy.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//------------------------------ 조회 ---------------------------------------------------
    @Override
    public Page<BoardDTO> search(String select, String query) {
        return null;
    }

    // userId 가 쓴 일반 게시물 조회
    @Override
    public Page<BoardDTO> selectAllByUserId(String userId) {
        Page<BoardVO> boardVOList
                = boardRepository.getUserBoardList(2,userId,
               setPageRequest());
        return toPageDTO(boardVOList);
       // log.debug("보드리스트 {}", list);
       // return list;
    }

    // 모든 일반 게시물 조회
    @Override
    public Page<BoardDTO> getBoardList() {
        // Page<BoardVO> 는 엔티티 자체를 그냥 넘기는 것 > 엔티티는 노출하지 않는 것이 좋으므로
        Page<BoardVO> boardVOList=boardRepository.findAllByBoardCode(2, setPageRequest());
        // DTO 로 변환


        return null;
    }

    @Override
    public BoardDTO findById(Long boardSeq) {
        BoardVO board = findVOById(boardSeq);
//        log.debug("findById 나와라 {}",boardVO.toString());
        BoardDTO boardDTO = new BoardDTO(board.getBoardSeq(), board.getUserId(),
                board.getContent(), board.getTitle(), board.getCreateDate(),board.getLikeCount()
        ,board.getStatus());
        //return boardVO;
        return  boardDTO;
    }
//------------------------------- save , delete ----------------------------------------------------
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
        BoardVO findBoard = boardRepository.findById(board.getBoardSeq()).orElse(null);
        if(findBoard != null){
            // 게시물 수정될 때는 title, content, updateDate , status 만 변경됨
            findBoard.setTitle(board.getTitle());
            findBoard.setContent(board.getContent());
            findBoard.setUpdateDate(nowDateAndTime());
            findBoard.setStatus(board.getStatus());

            boardRepository.save(findBoard);
            return board.getBoardSeq();
        }
        return null;
    }

    @Override
    public void deleteBoard(Long boardSeq) {

        boardRepository.deleteById(boardSeq);
    }

    //-------------------------------private method --------------------------------------------------

    //현재 시간 리턴하는 메서드
    private String nowDateAndTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return dateTime;
    }

 // PageRequest return
    private PageRequest setPageRequest(){
        return PageRequest.of(0,10,
                Sort.by(Sort.Direction.DESC,"boardSeq"));
    }


    // Page<VO> => Page<DTO > 로 변환
    private Page<BoardDTO> toPageDTO(Page<BoardVO> VO){
        Page<BoardDTO> toDto = VO.map(board -> new BoardDTO(board.getBoardSeq(), board.getUserId(),
                board.getTitle(), board.getContent(), board.getCreateDate()
                , board.getLikeCount(), board.getStatus()));

        return toDto;
    }

    private BoardVO findVOById(Long boardSeq){
        return  boardRepository.findById(boardSeq).orElse(null);
    }
}
