package com.roomy.repository;

import com.roomy.model.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserVO,String > {

    @Query("select u from UserVO u")
    Page<UserVO> findAllWithPage(Pageable pageable);
}
