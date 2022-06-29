package com.roomy.service.impl;

import com.roomy.dto.BoardDTO;
import com.roomy.dto.user.UserWithBoardDTO;
import com.roomy.entity.Board;
import com.roomy.entity.Room;
import com.roomy.entity.User;
import com.roomy.exception.UserNotFoundException;
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

    @Override @Transactional(readOnly = true)
    public Page<BoardDTO> search(String select, String query) {
        return null;
    }

    @Override @Transactional(readOnly = true)
    public Page<BoardDTO> selectAllByUsername(String username) {
       User user = userRepository.findById(username).orElse(null);
        if(user == null){
            new UserNotFoundException("해당 회원 없음");
        }
        Page<Board> boardVOList
                = boardRepository.getUserBoardList(2,user,
               setPageRequest());
        return null;
    }


    @Override
    public Slice<BoardDTO> loadBoardByRoom(Long roomId, Pageable pageable) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if(room == null){
            new RuntimeException("해당 room 없음");
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

    @Override @Transactional(readOnly = true)
    public Page<BoardDTO> getAllBoardList() {
        Page<Board> boardVOList=boardRepository.findAllByBoardCode(2, setPageRequest());
        return null;
    }

    @Override
    public UserWithBoardDTO getBoardBySeqAndPlusHit(Long boardSeq) {
        Board findBoard = boardRepository.findById(boardSeq).orElse(null);
        findBoard.plusHitCnt();
        boardRepository.save(findBoard);
        UserWithBoardDTO userWithBoardDTO = boardRepository.loadBoardDetail(boardSeq);
       return  userWithBoardDTO;
    }

    @Override
    public Long saveBoard(BoardDTO boardDTO) {
        log.debug("boardDTO insert 메서드 {}", boardDTO.toString());
        User user = userRepository.findById(boardDTO.getUsername()).orElse(null);
        Room room = roomRepository.findById(boardDTO.getRoomId()).orElse(null);
        if(room == null || user == null){
            new UserNotFoundException("해당 회원 없음");
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
            findBoard.updateContentAndTitle(board.getTitle(), board.getContent());
            boardRepository.save(findBoard);
            return board.getBoardSeq();
        }
        return null;
    }

    @Override
    public void deleteBoard(Long boardSeq) {
        boardRepository.deleteById(boardSeq);
    }

    private PageRequest setPageRequest(){
        return PageRequest.of(0,10,
                Sort.by(Sort.Direction.DESC,"boardSeq"));
    }


}
