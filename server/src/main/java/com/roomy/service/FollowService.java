package com.roomy.service;

import com.roomy.dto.user.UserDTO;

import java.util.Set;

public interface FollowService {


    void followOrUnfollow(String fromUser , String toUser);

    Boolean checkFollow(String fromUser, String toUser);

    Set<UserDTO> loadFollowings(String username);

    Set<UserDTO> loadFollowers(String username);
}
