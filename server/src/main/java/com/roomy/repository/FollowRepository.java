package com.roomy.repository;

import com.roomy.model.Follow;
import com.roomy.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FollowRepository extends JpaRepository<Follow,Long> {

  Boolean existsByFromUserAndToUser(User fromUser, User toUser);

  @Query(value = "select f.toUser from Follow f where f.fromUser =:user order by f.followId desc ")
  List<String> loadFollows(@Param(value = "user") User user , Pageable pageable);

  @Query(value = "select f.fromUser from Follow f where f.toUser =:user order by f.followId desc ")
  List<String> loadFollowers(@Param(value = "user") User user , Pageable pageable);


}
