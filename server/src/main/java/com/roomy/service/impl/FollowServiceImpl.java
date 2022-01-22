package com.roomy.service.impl;

import com.roomy.model.Follow;
import com.roomy.model.User;
import com.roomy.repository.FollowRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service @Slf4j
@Transactional
public class FollowServiceImpl implements FollowService {

    private final FollowRepository friendRepository;
    private final UserRepository userRepository;

    @Override
    public void followOrUnfollow(String roomUserName, String loggedInUserName) {
        User loggedInUser = userRepository.findByUsername(loggedInUserName);
        User roomUser = userRepository.findByUsername(roomUserName);
        Boolean checkExist = friendRepository.existsByFromUserAndToUser(roomUser,loggedInUser);
        if(checkExist){
            unfollow(loggedInUser,roomUser);
        }else{
            follow(loggedInUser,roomUser);
        }
    }

    private void follow(User loggedUser , User roomUser) {
        log.info("follow 실행");
        Follow follow = Follow.friendEntity(loggedUser, roomUser);// following, follower
        follow.userFollow(follow);
        friendRepository.save(follow);
    }

    private void unfollow(User loggedUser, User roomUser) {
        Follow follow = Follow.friendEntity(loggedUser, roomUser);
        follow.userUnFollow(loggedUser,roomUser);
        friendRepository.delete(follow);
    }
}
