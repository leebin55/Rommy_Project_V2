package com.roomy.room;

import com.roomy.dto.user.UserDTO;
import com.roomy.service.RoomService;
import com.roomy.service.UserService;
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
        userService.joinUser(UserDTO.builder().username("userA").password("11").build());
        userService.joinUser(UserDTO.builder().username("userB").password("11").build());
        roomService.loadRoomMainInfo("userA","userB");
    }
}
