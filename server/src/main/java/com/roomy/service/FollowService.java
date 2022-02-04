package com.roomy.service;

public interface FollowService {


    void followOrUnfollow(String fromUser , String toUser);

    Boolean checkFollow(String fromUser, String toUser);
}
