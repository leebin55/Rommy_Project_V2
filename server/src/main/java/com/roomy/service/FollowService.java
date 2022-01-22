package com.roomy.service;

import com.roomy.dto.FriendDTO;

public interface FollowService {


    void followOrUnfollow(String roomUser , String loggedInUser);
}
