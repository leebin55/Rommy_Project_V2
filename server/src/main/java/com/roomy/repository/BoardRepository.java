package com.roomy.repository;

import com.roomy.model.BoardVO;
import com.roomy.model.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//BoardRepository만 사용해도 일반 board, gallery 를 모두 가져올 수 있다. (type casting 사용해서)

public interface BoardRepository extends JpaRepository<BoardVO,Long > {


    Page<BoardVO> findAllByBoardCode(int boardCode, Pageable pageable);

    // 보드 코드와 유저 아이디를 받아 해당 유저의 미니홈피에서 게시글 조회
   // Page<BoardVO> findAllByBoardCodeAndUserIdOrderByBoardSeqDesc(int boardCode, String userId,Pageable pageable);
    @Query("select b from BoardVO b where b.boardCode=:boardCode and b.user =:user")
    Page<BoardVO> getUserBoardList(@Param("boardCode") int boardCode , @Param("user") UserVO user , Pageable pageable);


//
//    @Query("SELECT b FROM BoardVO b WHERE b.title LIKE %:query%  AND b.boardCode =:boardCode ORDER BY b.boardSeq DESC")
//    List<BoardVO> findByTitle(String query, @Param(value = "boardCode") int boardCode);
//
//    // 제목+내용 검색
//    @Query("SELECT b FROM BoardVO b WHERE (b.title LIKE %:query% OR b.content LIKE %:query% AND b.boardCode =:boardCode ) ORDER BY b.boardSeq DESC")
//    List<BoardVO> findByTitleAndContent(String query, @Param(value = "boardCode") int boardCode);
//
//    // 내용만 검색
//    @Query("SELECT b FROM BoardVO b WHERE b.content LIKE %:query%   AND b.boardCode =:boardCode ORDER BY b.boardSeq DESC")
//    List<BoardVO> findByContent(String query, @Param(value = "boardCode") int boardCode);




}