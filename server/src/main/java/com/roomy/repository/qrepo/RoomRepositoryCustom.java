package com.roomy.repository.qrepo;

import com.roomy.dto.GuestDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface RoomRepositoryCustom {

    List<GuestDTO> loadRecent4Guest(Long roomId);

    Slice<GuestDTO> loadGuestsByRoomId(Long roomId, Pageable pageable);

    UserWithRoomDTO loadUserAndRoom(Long roomId);



}
