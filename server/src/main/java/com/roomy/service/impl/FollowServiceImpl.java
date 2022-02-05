package com.roomy.service.impl;

import com.roomy.dto.user.UserDTO;
import com.roomy.entity.Follow;
import com.roomy.entity.User;
import com.roomy.repository.FollowRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service @Slf4j
@Transactional
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Override
    public void followOrUnfollow( String fromUsername, String toUsername) {
        checkFollowDTO checkFollowDTO = checkFollowWithUser(fromUsername, toUsername);
        if(checkFollowDTO.checkFollow){
            unfollow(checkFollowDTO.fromUser,checkFollowDTO.toUser);
        }else{
            follow(checkFollowDTO.fromUser,checkFollowDTO.toUser);
        }
    }

    @Override
    public Boolean checkFollow(String fromUsername, String toUsername) {
        checkFollowDTO checkFollowDTO = checkFollowWithUser(fromUsername, toUsername);
        return checkFollowDTO.checkFollow;
    }

    @Override
    public Set<UserDTO> loadFollowings(String username) {
        return    followRepository.findFollowersByUsername(username);

    }

    @Override
    public Set<UserDTO> loadFollowers(String username) {
        return null;
    }

    private checkFollowDTO checkFollowWithUser(String fromUsername, String toUsername){
        User fromUser = userRepository.findById(fromUsername).orElse(null);
        User toUser = userRepository.findById(toUsername).orElse(null);
        if(fromUser == null || toUser == null){
            new IllegalStateException("해당 유저를 찾을 수 없습니다.");
        }
        Boolean checkExist = followRepository.existsByFromUserAndToUser(fromUser,toUser);
        return new checkFollowDTO(checkExist,fromUser,toUser);
    }

    private void follow(User fromUser , User toUser) {
        log.info("follow 실행");
        Follow follow = Follow.friendEntity(fromUser, toUser);// following, follower
        follow.userFollow(follow);
        followRepository.save(follow);
    }

    private void unfollow(User fromUser, User toUser) {
        Follow follow = Follow.friendEntity(fromUser, toUser);
        follow.userUnFollow(fromUser,toUser);
        followRepository.delete(follow);
    }
    static class checkFollowDTO{
        private Boolean checkFollow;
        private User fromUser;
        private User toUser;

        public checkFollowDTO(Boolean checkFollow, User fromUser, User toUser) {
            this.checkFollow = checkFollow;
            this.fromUser = fromUser;
            this.toUser = toUser;
        }
    }

}

