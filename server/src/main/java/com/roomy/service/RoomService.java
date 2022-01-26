package com.roomy.service;

import com.roomy.dto.RecentBoardAndGuestDTO;
import com.roomy.dto.room.RoomProfileDTO;
import com.roomy.dto.user.UserWithRoomDTO;

import java.util.List;

public interface RoomService {

    RoomProfileDTO loadRoomLayoutInfo(String roomUser , String loggedInUser);

    RecentBoardAndGuestDTO loadRoomMainList(String username);

    List<UserWithRoomDTO> loadTop4();
    
}
