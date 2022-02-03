package com.roomy.service.impl;

import com.roomy.dto.RecentBoardAndGuestDTO;
import com.roomy.dto.RoomDTO;
import com.roomy.dto.user.UserWithRoomAndFollowDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.entity.Room;
import com.roomy.repository.BoardRepository;
import com.roomy.repository.GuestRepository;
import com.roomy.repository.RoomRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public UserWithRoomAndFollowDTO loadRoomLayoutInfo(String roomUsername , String loggedInUsername) {
        log.info("roomProfile service :{}",roomUsername);
        return userRepository.loadRoomProfileByUsername(roomUsername);

    }

    @Override
    public RecentBoardAndGuestDTO loadRoomMainList(Long roomId) {
        // 일단 갤러리 , 일반게시물 5개씩불러오기
        // 그리고 게스트 4개
        log.info("loadRoomMain room : {}",roomId);
        RecentBoardAndGuestDTO allBoards = roomRepository.findRecentAllBoardsByRoomId(roomId);
        log.info("load room main : {}", allBoards.toString());
        return allBoards;
    }

    @Override
    public List<UserWithRoomDTO> loadTop4() {
        return userRepository.loadTop4Room();
    }

    @Override
    public RoomDTO findByRoomId(Long roomId) {
        log.info("findByRoomId service 실행");
        Room room = roomRepository.findById(roomId).orElse(null);
        if(room == null){
            new IllegalStateException("해당 room 없음");
        }
        return RoomDTO.toDTO(room);
    }

    @Override
    public void updateRoom(RoomDTO roomDTO) {
        Room room = roomRepository.findById(roomDTO.getRoomId()).orElse(null);
        if(room == null){
            new IllegalStateException("해당 room 없음");
        }
        room.updateRoom(roomDTO.getRoomName(),roomDTO.getIntro());
        roomRepository.save(room);
    }


}
