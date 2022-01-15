package com.roomy.service.impl;

import com.roomy.dto.FriendDTO;
import com.roomy.model.Friend;
import com.roomy.model.User;
import com.roomy.repository.FriendRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service @Slf4j
@Transactional
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;



    @Override
    public void followOrUnfollow(String roomUserName, String loggedInUserName) {
        User loggedInUser = userRepository.findByUsername(loggedInUserName);
        User roomUser = userRepository.findByUsername(roomUserName);
        Boolean checkExist = friendRepository.existsByFollowingAndFollower(roomUser,loggedInUser);
        if(checkExist){
            unfollow(loggedInUser,roomUser);
        }else{
            follow(loggedInUser,roomUser);
        }
    }

    private void follow(User loggedUser , User roomUser) {
        Friend friend = Friend.createFriend(loggedUser, roomUser);// following, follower
        friend.setUser(loggedUser,roomUser);
        friendRepository.save(friend);
    }

    private void unfollow(User loggedUser, User roomUser) {
        Friend friend = Friend.createFriend(loggedUser, roomUser);
        friendRepository.delete(friend);
    }

}
