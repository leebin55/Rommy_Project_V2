package com.roomy.service.impl;

import com.roomy.dto.RoomMainDTO;
import com.roomy.model.User;
import com.roomy.repository.FriendRepository;
import com.roomy.repository.RoomRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service @Slf4j
public class RoomServiceImpl implements RoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    // room main 화면에서 user 와 room friend 리스트 불러오기
    // user , room, friend join 해서 한번에 데이터 불러오기
    @Override
    public RoomMainDTO loadRoomMainInfo(String roomUsername ,String loggedInUsername) {
        // 로그인한 유저가 해당 룸 유저를 팔로우 했는지 확인
        User roomUser = userRepository.findByUsername(roomUsername);
        User loggedInUser = userRepository.findByUsername(loggedInUsername);
        userRepository.loadRoomMain(roomUsername);
        return null;
    }


}
