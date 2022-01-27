package com.roomy.repository;

import com.roomy.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ImgRepository extends JpaRepository<BoardImage,Long> {

    void deleteByBoard(Long boardSeq);
}
