package com.roomy.service;

import com.roomy.dto.BoardDTO;
import com.roomy.model.BoardVO;


import java.util.List;

//public interface BoardService<T extends Board> extends GenericService<T, Long> {
public interface BoardService {

//    public Page<BoardVO> selectAll(Pageable pageable);
//      페이지네이션

    public Long saveBoard(BoardDTO board);

    public Long updateBoard(BoardDTO board);

    public  void deleteBoard(Long boardSeq);

    public BoardDTO findById(Long boardSeq);

    public List<BoardDTO> getBoardList();

    public List<BoardVO> search(String select, String query);

    public List<BoardDTO> selectAllByUserId(String userId);

}
