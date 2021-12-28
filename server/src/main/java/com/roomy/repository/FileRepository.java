package com.roomy.repository;

import com.roomy.model.BoardImageVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FileRepository extends JpaRepository<BoardImageVO,Long> {

    // boardSeq 를 받아서 imageURL return
    @Query(value = "SELECT imgUrl FROM BoardImageVO " +
            "WHERE board = :boardSeq " )
    List<String> findByImgBoardSeq(@Param(value="boardSeq") Long boardSeq);


    void deleteByBoard(Long boardSeq);
}
