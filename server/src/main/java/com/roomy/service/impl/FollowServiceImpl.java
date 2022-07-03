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
    public void follow(String fromUser , String toUser) {
        FollowDTO dto = this.getUserEntities(fromUser, toUser);
        Follow follow = Follow.createFollowEntity(dto.fromUser, dto.toUser);
        follow.userFollow(follow);
        followRepository.save(follow);
    }

    @Override
    public void unfollow(String fromUser, String toUser) {
        FollowDTO dto = this.getUserEntities(fromUser, toUser);
        followRepository.deleteByFromUserAndToUser(dto.fromUser,dto.toUser);
    }
    
    @Override
    public Boolean checkFollow(String fromUsername, String toUsername) {
        FollowDTO dto = getUserEntities(fromUsername, toUsername);
        Boolean checkExist = followRepository.existsByFromUserAndToUser(dto.fromUser,dto.toUser);
        return checkExist;
    }

    @Override
    public Set<UserDTO> loadFollowings(String username) {
        return    followRepository.findFollowersByUsername(username);
    }

    @Override
    public Set<UserDTO> loadFollowers(String username) {
        return null;
    }

    private FollowDTO getUserEntities(String fromUsername, String toUsername){
        User fromUser = userRepository.findById(fromUsername).orElse(null);
        User toUser = userRepository.findById(toUsername).orElse(null);
        if(fromUser == null || toUser == null){
            new IllegalStateException("해당 유저를 찾을 수 없습니다.");
        }
        return new FollowDTO(fromUser,toUser);
    }

    static class FollowDTO {
//        private Boolean checkFollow;
        private User fromUser;
        private User toUser;

        public FollowDTO(User fromUser, User toUser){
            this.fromUser = fromUser;
            this.toUser = toUser;
        }

//        public void setCheckFollow(Boolean isFollow){
//            this.checkFollow = isFollow;
//        }
    }

}

