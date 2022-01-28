package com.roomy.service;

import com.roomy.dto.GuestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface GuestService {

    Long saveGuest(GuestDTO guestDto, String username);

    Long updateGuest(GuestDTO guestDto);

    Long deleteGuest(Long guestId);

    GuestDTO findByGuestSeq(Long guestSeq);

    List<GuestDTO> getRecentGuest(Long roomId);

    Slice<GuestDTO> getAllGuestByRoom(Long roomId, Pageable pageable);

    List<GuestDTO> getGuestByUsername(String username);
}
