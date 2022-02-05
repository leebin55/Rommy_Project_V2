package com.roomy.service;

import com.roomy.dto.user.UserWithRoomDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class RoomTest {

    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @Test
    void loadRoomMainTest(){
        userService.joinUser(UserWithRoomDTO.builder().username("userA").password("11").build());
        userService.joinUser(UserWithRoomDTO.builder().username("userB").password("11").build());
        roomService.loadRoomMainList("userA");
    }
}
