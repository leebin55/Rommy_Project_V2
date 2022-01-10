package com.roomy.service;

import com.roomy.dto.GuestDTO;
import com.roomy.model.Room;
import org.springframework.data.domain.Slice;

public interface GuestService {

    Long saveGuest(GuestDTO guestDto);

    Long updateGuest(GuestDTO guestDto);

    Long deleteGuest(Long guestId);

    GuestDTO findByGuestSeq(Long guestSeq);

    Slice<GuestDTO> getRoomGuestList(Room room);
}
