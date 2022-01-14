package com.roomy.repository;

import com.roomy.model.Friend;
import com.roomy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend,Long> {


   Boolean existsByUserAndFollowUser(User user,String roomUser);
}
