package com.roomy.service.impl;

import com.roomy.dto.BoardDTO;
import com.roomy.model.BoardVO;
import com.roomy.model.UserVO;
import com.roomy.repository.BoardRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service("boardService") @Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

//------------------------------ 조회 ---------------------------------------------------
    @Override @Transactional(readOnly = true)
    public Page<BoardDTO> search(String select, String query) {
        return null;
    }

    // username 가 쓴 일반 게시물 조회 : username 을 사용한 이유 > url 에 room/{username} 이런 패턴이기 때문
    @Override @Transactional(readOnly = true)
    public Page<BoardDTO> selectAllByUsername(String username) {
        // 만약 해당 username 이없다면 회원이 아닌것 > 처리해주기
       UserVO user = userRepository.findByUsername(username);
        if(user == null){
            return null;
        }
        Page<BoardVO> boardVOList
                = boardRepository.getUserBoardList(2,user,
               setPageRequest());
        return toPageDTO(boardVOList);
       // log.debug("보드리스트 {}", list);
       // return list;
    }

    // 모든 일반 게시물 조회
    @Override @Transactional(readOnly = true)
    public Page<BoardDTO> getAllBoardList() {
        // Page<BoardVO> 는 엔티티 자체를 그냥 넘기는 것 > 엔티티는 노출하지 않는 것이 좋으므로
        Page<BoardVO> boardVOList=boardRepository.findAllByBoardCode(2, setPageRequest());
        // DTO 로 변환


        return null;
    }

    @Override @Transactional(readOnly = true)
    public BoardDTO getBoardBySeq(Long boardSeq) {
        BoardVO board = findVOById(boardSeq);
//        log.debug("findById 나와라 {}",boardVO.toString());
//        BoardDTO boardDTO = new BoardDTO(board.getBoardSeq(),
//                board.getContent(), board.getTitle(), board.getCreateDate(), board.getLikeList().size()
//        ,board.getStatus());
        //return boardVO;
     //   return  boardDTO;
        return null;
    }
//------------------------------- save , delete ----------------------------------------------------
    @Override
    public Long saveBoard(BoardDTO board) {

        log.debug("board insert 메서드 {}", board.toString());
        board.setCreateDate(nowDateAndTime());
        // 일반 게시글 등록일때
        BoardVO boardVO = board.toEntity();
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

    // url로 직접 치고 room으로 들어갈수 있기 때문에 /room/{username}
    // username 이 존재하는지 확인>> 이건 /room/{username}으로 요청되는 모든 것에
    // 적용해야 되는데??
    // AOP????

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
//        Page<BoardDTO> toDto = VO.map(board -> new BoardDTO(board.getBoardSeq(),
//                board.getTitle(), board.getContent(), board.getCreateDate()
//                , board.getLikeList().size(), board.getStatus()));

       // return toDto;
        return  null;
    }

    private BoardVO findVOById(Long boardSeq){
        return  boardRepository.findById(boardSeq).orElse(null);
    }
}
