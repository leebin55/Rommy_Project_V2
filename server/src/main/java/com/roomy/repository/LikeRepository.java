package com.roomy.repository;

import com.roomy.model.BoardVO;
import com.roomy.model.LikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeVO,Long> {
//
//    // board 와 user seq 로 해당 데이터가 존재 하는지 확인
//    boolean existsByBoardAndUserId(BoardVO board, String userId);
//
//    List<LikeVO> findAllByUserId(String userId);
//
//    void deleteByUserIdAndBoard(String userId , BoardVO boardVO);


}