package com.roomy.repository;

import com.roomy.model.Board;
import com.roomy.model.Like;
import com.roomy.model.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    boolean existsByBoardAndAndUser(Board board, User user);

    Optional<Like> findByBoardAndUser(Board board, User user);

//  해당 user 가 좋아요 누른 리스트
    Slice<Like> findAllByUser(User user);

    Long countByBoard(Board board);

//
//    void deleteByUserIdAndBoard(String userId , BoardVO boardVO);


}