package com.roomy.service;

import com.roomy.dto.RecentBoardAndGuestDTO;
import com.roomy.dto.RoomDTO;
import com.roomy.dto.user.UserWithRoomAndFollowDTO;
import com.roomy.dto.user.UserWithRoomDTO;

import java.util.List;

public interface RoomService {

    UserWithRoomAndFollowDTO loadRoomLayoutInfo(String roomUser , String loggedInUser);

    RecentBoardAndGuestDTO loadRoomMainList(Long roomId);

    List<UserWithRoomDTO> loadTop4();

    RoomDTO findByRoomId(Long roomId);

    void updateRoom(RoomDTO roomDTO);
    
}
