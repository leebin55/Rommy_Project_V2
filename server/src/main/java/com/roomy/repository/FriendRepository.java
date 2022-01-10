package com.roomy.repository;

import com.roomy.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend,Long> {

}
