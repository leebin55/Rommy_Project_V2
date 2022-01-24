package com.roomy.repository;

import com.roomy.entity.User;
import com.roomy.repository.qrepo.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>, UserRepositoryCustom {


    <Optional> User findByUsername(String username);

    Boolean existsByUsername(String username);

    // dto로 return > querydsl
//    @EntityGraph(attributePaths = {"followingList","room"})
//    @Query(value = "select u from User u where u.username =:username")
//    User loadRoomMain(@Param("username") String username);
}
