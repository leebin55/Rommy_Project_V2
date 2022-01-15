package com.roomy.repository;

import com.roomy.model.Friend;
import com.roomy.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FriendRepository extends JpaRepository<Friend,Long> {

  Boolean existsByFollowingAndFollower(User following, User follower);

  @Query(value = "select f.following from Friend f where f.follower =:user order by f.friendId desc ")
  Slice<String> loadFollows(@Param(value = "user") User user , Pageable pageable);

  @Query(value = "select f.follower from Friend f where f.following =:user order by f.friendId desc ")
  Slice<String> loadFollowers(@Param(value = "user") User user , Pageable pageable);

  void deleteByFollowingAndFollower(User following, User follower);

}
