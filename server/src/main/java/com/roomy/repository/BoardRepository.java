package com.roomy.repository;

import com.roomy.entity.Board;
import com.roomy.entity.Room;
import com.roomy.entity.User;
import com.roomy.repository.qrepo.BoardRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//BoardRepository만 사용해도 일반 board, gallery 를 모두 가져올 수 있다. (type casting 사용해서)

public interface BoardRepository extends JpaRepository<Board,Long >, BoardRepositoryCustom {


    Page<Board> findAllByBoardCode(int boardCode, Pageable pageable);

    // 보드 코드와 유저 아이디를 받아 해당 유저의 미니홈피에서 게시글 조회
   // Page<BoardVO> findAllByBoardCodeAndUserIdOrderByBoardSeqDesc(int boardCode, String userId,Pageable pageable);
    @Query("select b from Board b where b.boardCode=:boardCode and b.user =:user")
    Page<Board> getUserBoardList(@Param("boardCode") int boardCode , @Param("user") User user , Pageable pageable);

    Slice<Board> findByRoomOrderByCreateDateDesc(Room room, Pageable pageable);



}