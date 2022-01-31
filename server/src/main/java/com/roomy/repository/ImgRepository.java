package com.roomy.repository;

import com.roomy.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImgRepository extends JpaRepository<Image,Long> {

    void deleteByBoard(Long boardSeq);
}
