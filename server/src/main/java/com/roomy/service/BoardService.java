package com.roomy.service;

import com.roomy.model.BoardVO;


import java.util.List;

//public interface BoardService<T extends Board> extends GenericService<T, Long> {
public interface BoardService extends GenericService<BoardVO, Long> {

//    public Page<BoardVO> selectAll(Pageable pageable);
//      페이지네이션
    
    public List<BoardVO> search(String select, String query);

    public List<BoardVO> selectAllByUserId(String userId);

}
