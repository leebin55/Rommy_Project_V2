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

@RequiredArgsConstructor
@Service @Slf4j
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepo;
    private final UserRepository userRepo;

    @Override
    public void followOrUnfollow(FriendDTO friendDTO) {
        User loggedUser = userRepo.findByUsername(friendDTO.getLoggedInUser());
        User roomUser = userRepo.findByUsername(friendDTO.getRoomUser());
        Boolean checkExist = friendRepo.existsByUserAndFollowUser(loggedUser, friendDTO.getRoomUser());
        if(checkExist){
            unfollow(loggedUser,roomUser);
        }else{
            follow(loggedUser,roomUser);
        }
    }

    private void follow(User loggedUser , User roomUser) {
        // 친구 테이블에 로그인한 회원의  follow한 유저 추가
        Friend followFriend = Friend.builder().user(loggedUser)
                .followUser(roomUser.getUsername()).build();
        friendRepo.save(followFriend);

        // 친구테이블에 룸 주인 회원의 follower 한 유저 추가
        Friend followerFriend = Friend.builder().user(roomUser).followerUser(loggedUser.getUsername())
                .build();
        friendRepo.save(followerFriend);
    }

    private void unfollow(User loggedUser, User roomUser) {

    }

}
