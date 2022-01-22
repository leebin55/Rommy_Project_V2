package com.roomy.service.impl;

import com.roomy.dto.GuestDTO;
import com.roomy.model.Guest;
import com.roomy.model.Room;
import com.roomy.repository.GuestRepository;
import com.roomy.repository.RoomRepository;
import com.roomy.service.GuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @Slf4j @Transactional
public class GuestServiceImpl implements GuestService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    public GuestServiceImpl(RoomRepository roomRepository, GuestRepository guestRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public Long saveGuest(GuestDTO guestDto) {
        Guest guest = guestDto.toEntity();
        return null;
    }

    @Override
    public Long updateGuest(GuestDTO guestDto) {
        return null;
    }

    @Override
    public Long deleteGuest(Long guestSeq) {

        guestRepository.deleteById(guestSeq);
        return guestSeq;
    }

    @Override
    public GuestDTO findByGuestSeq(Long guestSeq) {
        return null;
    }

    @Override
    public List<GuestDTO> getRoomMainGuest(String username) {
        return null;
    }

    @Override
    public List<GuestDTO> getGuestByUsername(String username) {
        return null;
    }

}
