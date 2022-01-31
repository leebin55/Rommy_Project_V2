package com.roomy.repository;

import com.roomy.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImgRepository extends JpaRepository<Image,Long> {

}
