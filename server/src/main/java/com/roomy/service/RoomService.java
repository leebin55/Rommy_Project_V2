package com.roomy.service;

import com.roomy.dto.RoomMainListDTO;
import com.roomy.dto.RoomProfileDTO;

public interface RoomService {

    RoomProfileDTO loadRoomLayoutInfo(String roomUser , String loggedInUser);

    RoomMainListDTO loadRoomMainList(String username);
}
