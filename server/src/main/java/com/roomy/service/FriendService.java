package com.roomy.service;

import com.roomy.dto.FriendDTO;

public interface FriendService {


    void followOrUnfollow(String roomUser , String loggedInUser);
}
