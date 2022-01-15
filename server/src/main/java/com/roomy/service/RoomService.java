package com.roomy.service;

import com.roomy.dto.RoomMainDTO;

public interface RoomService {
    RoomMainDTO loadRoomMainInfo(String roomUser , String loggedInUser);
}
