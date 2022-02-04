package com.roomy.service.impl;

import com.roomy.dto.BoardDTO;
import com.roomy.dto.user.UserWithBoardDTO;
import com.roomy.entity.Board;
import com.roomy.entity.Room;
import com.roomy.entity.User;
import com.roomy.repository.BoardRepository;
import com.roomy.repository.RoomRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value = "boardService") @Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

//------------------------------ 조회 ---------------------------------------------------
    @Override @Transactional(readOnly = true)
    public Page<BoardDTO> search(String select, String query) {
        return null;
    }

    // username 가 쓴 일반 게시물 조회 : username 을 사용한 이유 > url 에 room/{username} 이런 패턴이기 때문
    @Override @Transactional(readOnly = true)
    public Page<BoardDTO> selectAllByUsername(String username) {
        // 만약 해당 username 이없다면 회원이 아닌것 > 처리해주기
       User user = userRepository.findById(username).orElse(null);
        if(user == null){
            log.info("selectAllByUsername user없음");
            return null;
        }
        Page<Board> boardVOList
                = boardRepository.getUserBoardList(2,user,
               setPageRequest());
        return null;
       // log.debug("보드리스트 {}", list);
       // return list;
    }


    @Override
    public Slice<BoardDTO> loadBoardByRoom(Room room, Pageable pageable) {
        if(room == null){
            new IllegalStateException("해당 room은 존재하지않습니다.");
        }
        Slice<Board> boardWithPage = boardRepository.findByRoomOrderByCreateDateDesc(room, pageable);

        return boardWithPage.map(board -> {
            String date = board.getCreateDate().toString().substring(0, 10);
           return BoardDTO.builder()
                .boardSeq(board.getBoardSeq())
                .title(board.getTitle())
                .boardHit(board.getBoardHit())
                .likeCount(board.getLikeCount())
                .createDate(date).build();
        });
    }

    // 모든 일반 게시물 조회
    @Override @Transactional(readOnly = true)
    public Page<BoardDTO> getAllBoardList() {
        // Page<BoardVO> 는 엔티티 자체를 그냥 넘기는 것 > 엔티티는 노출하지 않는 것이 좋으므로
        Page<Board> boardVOList=boardRepository.findAllByBoardCode(2, setPageRequest());
        // DTO 로 변환
        return null;
    }

    @Override @Transactional(readOnly = true)
    public UserWithBoardDTO getBoardBySeq(Long boardSeq) {
        UserWithBoardDTO userWithBoardDTO = boardRepository.loadBoardDetail(boardSeq);
        log.info("boardwituser : {}",userWithBoardDTO.toString());
       return  userWithBoardDTO;

    }
//------------------------------- save , delete ----------------------------------------------------
    @Override
    public Long saveBoard(BoardDTO boardDTO) {
        log.debug("boardDTO insert 메서드 {}", boardDTO.toString());
        User user = userRepository.findById(boardDTO.getUsername()).orElse(null);
        Room room = roomRepository.findById(boardDTO.getRoomId()).orElse(null);
        if(room == null || user == null){
            new IllegalStateException("해당 유저가 없음");
        }
        Board board = boardDTO.createBoard();
        board.setRoomAndUser(room , user);
        boardRepository.save(board);
        return board.getBoardSeq();
    }

    @Override
    public Long updateBoard(BoardDTO board) {
        Board findBoard = boardRepository.findById(board.getBoardSeq()).orElse(null);
        if(findBoard != null){
            // 게시물 수정될 때는 title, content, updateDate , status 만 변경됨
            findBoard.toBuilder().title(board.getTitle()).content(board.getContent())
                            .status(board.getStatus()).build();

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

 // PageRequest return
    private PageRequest setPageRequest(){

        return PageRequest.of(0,10,
                Sort.by(Sort.Direction.DESC,"boardSeq"));
    }



    private Board findVOById(Long boardSeq){
        return  boardRepository.findById(boardSeq).orElse(null);
    }
}
