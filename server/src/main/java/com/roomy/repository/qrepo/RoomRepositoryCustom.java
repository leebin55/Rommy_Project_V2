package com.roomy.repository.qrepo;

import com.roomy.dto.BoardDTO;
import com.roomy.dto.GalleryDTO;
import com.roomy.dto.GuestDTO;
import com.roomy.dto.RecentBoardAndGuestDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface RoomRepositoryCustom {

    List<GuestDTO> findRecentGuestByRoomId(Long roomId);

    RecentBoardAndGuestDTO findRecentAllBoardsByRoomId(Long roomId);

    Slice<GuestDTO> findGuestByRoomIdWithPage(Long roomId, Pageable pageable);

    UserWithRoomDTO loadUserAndRoom(Long roomId);



}
