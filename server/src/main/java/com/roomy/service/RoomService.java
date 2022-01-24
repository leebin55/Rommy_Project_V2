package com.roomy.service;

import com.roomy.dto.RecentBoardAndGuestDTO;
import com.roomy.dto.room.RoomProfileDTO;

public interface RoomService {

    RoomProfileDTO loadRoomLayoutInfo(String roomUser , String loggedInUser);

    RecentBoardAndGuestDTO loadRoomMainList(String username);
}
