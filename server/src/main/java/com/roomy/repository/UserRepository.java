package com.roomy.repository;

import com.roomy.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,String > {


    <Optional> User findByUsername(String username);

    Boolean existsByUsername(String username);

    @EntityGraph(attributePaths = {"followingList","followerList","room"})
    @Query(value = "select u from User u")
    User loadRoomMain(String username);
}
