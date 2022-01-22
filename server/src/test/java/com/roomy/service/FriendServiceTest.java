package com.roomy.service;

import com.roomy.dto.user.UserDTO;
import com.roomy.model.User;
import com.roomy.repository.UserRepository;
import com.roomy.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class FriendServiceTest {

    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;

    @Test
    void followOrUnfollowTest(){

        String userA = userService.joinUser(UserDTO.builder().username("a").nickname("login")
                .password("11").build());
        String userB = userService.joinUser(UserDTO.builder().username("b").nickname("room")
                .password("11").build());
        followService.followOrUnfollow(userB,userA);//roomuser, loggedUser
//        Assertions.assertEquals(userA.getFollowingList().size(),1);

    }
}