package com.roomy.service;

import com.roomy.dto.BoardDTO;
import com.roomy.model.BoardVO;
import org.springframework.data.domain.Page;


import java.util.List;

//public interface BoardService<T extends Board> extends GenericService<T, Long> {
public interface BoardService {

//    public Page<BoardVO> selectAll(Pageable pageable);
//      페이지네이션

    public Long saveBoard(BoardDTO board);

    public Long updateBoard(BoardDTO board);

    public  void deleteBoard(Long boardSeq);

    public BoardDTO findById(Long boardSeq);

    public Page<BoardDTO> getBoardList();

    public Page<BoardDTO> search(String select, String query);

    public Page<BoardDTO> selectAllByUserId(String userId);

}
