package com.roomy.repository;

import com.roomy.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FileRepository extends JpaRepository<BoardImage,Long> {

    // boardSeq 를 받아서 imageURL return
    @Query(value = "SELECT imgUrl FROM BoardImage " +
            "WHERE board = :boardSeq " )
    List<String> findByImgBoardSeq(@Param(value="boardSeq") Long boardSeq);


    void deleteByBoard(Long boardSeq);
}
