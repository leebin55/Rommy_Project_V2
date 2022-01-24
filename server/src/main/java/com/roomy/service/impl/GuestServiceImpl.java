package com.roomy.service.impl;

import com.roomy.dto.GuestDTO;
import com.roomy.entity.Guest;
import com.roomy.entity.Room;
import com.roomy.repository.GuestRepository;
import com.roomy.repository.RoomRepository;
import com.roomy.service.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service @Slf4j @Transactional(readOnly = true)
public class GuestServiceImpl implements GuestService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;


    @Override @Transactional
    public Long saveGuest(GuestDTO guestDto) {
        Room room = roomRepository.findById(guestDto.getRoomId()).orElse(null);
        if(room == null){
            // room 이 없을 때 exception처리
            new IllegalStateException("해당 room  이 존재하지 않습니다.");
        }
        Guest guest = guestDto.toEntity();
        guest.setRoom(room);
        guestRepository.save(guest);
        return guest.getGuestSeq();
    }

    @Override @Transactional
    public Long updateGuest(GuestDTO guestDto) {
        return null;
    }

    @Override @Transactional
    public Long deleteGuest(Long guestSeq) {

        guestRepository.deleteById(guestSeq);
        return guestSeq;
    }

    @Override
    public GuestDTO findByGuestSeq(Long guestSeq) {
        return null;
    }

    @Override
    public List<GuestDTO> getRecentGuest(Long roomId) {
        List<GuestDTO> guestList = roomRepository.loadRecent4Guest(roomId);
        log.info("recentGuest service : {}", guestList.toString());
        return guestList;
    }

    @Override
    public List<GuestDTO> getAllGuestByRoom(Long roomId) {
        return null;
    }

    @Override
    public List<GuestDTO> getGuestByUsername(String username) {
        return null;
    }

}
