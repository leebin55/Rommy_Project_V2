package com.roomy.service;

import com.roomy.dto.GuestDTO;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface GuestService {

    Long saveGuest(GuestDTO guestDto);

    Long updateGuest(GuestDTO guestDto);

    Long deleteGuest(Long guestId);

    GuestDTO findByGuestSeq(Long guestSeq);

    List<GuestDTO> getRecentGuest(Long roomId);

    List<GuestDTO> getAllGuestByRoom(Long roomId);

    List<GuestDTO> getGuestByUsername(String username);
}
