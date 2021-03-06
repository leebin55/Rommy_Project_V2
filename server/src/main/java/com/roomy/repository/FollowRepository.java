package com.roomy.repository;

import com.roomy.entity.Follow;
import com.roomy.entity.User;
import com.roomy.repository.qrepo.FollowRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FollowRepository extends JpaRepository<Follow,Long> , FollowRepositoryCustom {

  Boolean existsByFromUserAndToUser(User fromUser, User toUser);

  @Query(value = "select f.toUser from Follow f where f.fromUser =:user order by f.followId desc ")
  List<String> loadFollows(@Param(value = "user") User user , Pageable pageable);

  @Query(value = "select f.fromUser from Follow f where f.toUser =:user order by f.followId desc ")
  List<String> loadFollowers(@Param(value = "user") User user , Pageable pageable);

  void deleteByFromUserAndToUser(User fromUser, User toUser);
}
