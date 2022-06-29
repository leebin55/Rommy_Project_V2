package com.roomy.service;

import com.roomy.dto.BoardDTO;
import com.roomy.dto.user.UserWithBoardDTO;
import com.roomy.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

//public interface BoardService<T extends Board> extends GenericService<T, Long> {
public interface BoardService {

//    public Page<BoardVO> selectAll(Pageable pageable);
//      페이지네이션

    public Long saveBoard(BoardDTO board);

    public Long updateBoard(BoardDTO board);

    public  void deleteBoard(Long boardSeq);

    public UserWithBoardDTO getBoardBySeqAndPlusHit(Long boardSeq);

    public Page<BoardDTO> getAllBoardList();

    public Page<BoardDTO> search(String select, String query);

    public Page<BoardDTO> selectAllByUsername(String username);

    Slice<BoardDTO> loadBoardByRoom(Long room, Pageable pageable);
}
