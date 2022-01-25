package com.roomy.repository;

import com.roomy.entity.User;
import com.roomy.repository.qrepo.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String>, UserRepositoryCustom {


    <Optional> User findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query(value = "select u.nickname from User u where u.username =:username")
    String nicknameByUsername(@Param(value = "username") String username);

    // dto로 return > querydsl
//    @EntityGraph(attributePaths = {"followingList","room"})
//    @Query(value = "select u from User u where u.username =:username")
//    User loadRoomMain(@Param("username") String username);
}
