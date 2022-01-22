package com.roomy.service.impl;

import com.roomy.dto.RoomMainListDTO;
import com.roomy.dto.RoomProfileDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.model.User;
import com.roomy.repository.BoardRepository;
import com.roomy.repository.GuestRepository;
import com.roomy.repository.RoomRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.GuestService;
import com.roomy.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service @Slf4j
public class RoomServiceImpl implements RoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final BoardRepository boardRepository;

    // room main 화면에서 user 와 room friend 리스트 불러오기
    // user , room, follow join 해서 한번에 데이터 불러오기
    //  + 로그인한 유저가 room user follow 햇는지 확인
    @Override
    @Transactional(readOnly = true) // 조회만 함 : flush 를 하지않음
    public RoomProfileDTO loadRoomLayoutInfo(String roomUsername , String loggedInUsername) {
        User roomUser = userRepository.findByUsername(roomUsername);
        User loggedInUser = userRepository.findByUsername(loggedInUsername);
//         userRepository.loadRoomMain(roomUsername);
        return null;
    }

    @Override
    public RoomMainListDTO loadRoomMainList(String username) {
        //hibernate 에서 List 여러개 join 해서 불러올 수 없어서 따로따로 부름(multipleBags..)
        UserWithRoomDTO userWithRoom = userRepository.userWithRoomByUsername(username);
        log.info("loadRoomMain room : {}", userWithRoom.toString());
        return null;
    }


}
