package com.roomy.repository;

import com.roomy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,String > {

    @Query("select u from User u")
    Page<User> findAllWithPage(Pageable pageable);

    <Optional> User findByUsername(String username);

    Boolean existsByUsername(String username);
}
