package com.roomy.service.impl;

import com.roomy.dto.RoomWithUserAndFriendsDTO;
import com.roomy.repository.UserRepository;
import com.roomy.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service @Slf4j
public class RoomServiceImpl implements RoomService {

    private final UserRepository userRepo;

    // room main 화면에서 user 와 room friend 리스트 불러오기
    @Override
    public RoomWithUserAndFriendsDTO loadRoomMainInfo(String username) {

        return null;
    }
}
