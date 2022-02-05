package com.roomy.service;

import com.roomy.dto.user.UserWithRoomDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        String userA = userService.joinUser(UserWithRoomDTO.builder().username("a").nickname("login")
                .password("11").build());
        String userB = userService.joinUser(UserWithRoomDTO.builder().username("b").nickname("room")
                .password("11").build());
        followService.followOrUnfollow(userB,userA);//roomuser, loggedUser
//        Assertions.assertEquals(userA.getFollowingList().size(),1);

    }
}