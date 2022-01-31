package com.roomy.service.impl;

import com.roomy.dto.GuestDTO;
import com.roomy.entity.Guest;
import com.roomy.entity.Room;
import com.roomy.repository.GuestRepository;
import com.roomy.repository.RoomRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service @Slf4j @Transactional(readOnly = true)
public class GuestServiceImpl implements GuestService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final GuestRepository guestRepository;



    @Override @Transactional
    public Long saveGuest(GuestDTO guestDto,String username) {
        // username을 따로 받은 이유
        // 글을 등록하면 nickname 으로 보여지는기 때문에 username 과 nickname 을 같이 toBuild
        // room 의 user(room 주인) 와 username(로그인한 유저)은 다른유저
        Room room = roomRepository.findById(guestDto.getRoomId()).orElse(null);
        if(room == null) {
            new IllegalStateException("해당 room  이 존재하지 않습니다.");
        }
        GuestDTO guestWithUser = guestDto.toBuilder().username(username)
                .nickname(userRepository.nicknameByUsername(username)).build();
        Guest guest = guestWithUser.toEntity();
        guest.setRoom(room);
        guestRepository.save(guest);
        return guest.getGuestSeq();
    }

    @Override @Transactional
    public Long updateGuest(GuestDTO guestDto) {
        Guest guest = guestRepository.findById(guestDto.getGuestSeq()).orElse(null);
        if(guest != null){
            guest.updateGuest(guestDto.getContent(),guestDto.getStatus());
            guestRepository.save(guest);
            return guest.getGuestSeq();
        }
        return null;
    }

    @Override @Transactional
    public Long deleteGuest(Long guestSeq) {
        guestRepository.deleteById(guestSeq);
        return guestSeq;
    }

    @Override
    public GuestDTO findByGuestSeq(Long guestSeq) {
        Guest guest = guestRepository.findById(guestSeq).orElse(null);
        return GuestDTO.toDTO(guest);
    }

    @Override
    public List<GuestDTO> getRecentGuest(Long roomId) {
        List<GuestDTO> guestList = roomRepository.findRecentGuestByRoomId(roomId);
        log.info("recentGuest service : {}", guestList.toString());
        return guestList;
    }

    @Override
    public Slice<GuestDTO> getAllGuestByRoom(Long roomId , Pageable pageable) {
        log.info("방명록 불러오기 ");
        return roomRepository.findGuestByRoomIdWithPage(roomId, pageable);
    }

    @Override
    public List<GuestDTO> getGuestByUsername(String username) {
        return null;
    }

}
