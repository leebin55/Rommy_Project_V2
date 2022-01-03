package com.roomy.repository;

import com.roomy.model.BoardVO;
import com.roomy.model.LikeVO;
import com.roomy.model.UserVO;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeVO,Long> {

    boolean existsByBoardAndAndUser(BoardVO board, UserVO user);

    Optional<LikeVO> findByBoardAndUser(BoardVO board, UserVO user);

//  해당 user 가 좋아요 누른 리스트
    Slice<LikeVO> findAllByUser(UserVO user);

    Long countByBoard(BoardVO board);

//
//    void deleteByUserIdAndBoard(String userId , BoardVO boardVO);


}