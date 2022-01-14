package com.roomy.service;

import com.roomy.dto.RoomWithUserAndFriendsDTO;

public interface RoomService {
    RoomWithUserAndFriendsDTO loadRoomMainInfo(String username);
}
