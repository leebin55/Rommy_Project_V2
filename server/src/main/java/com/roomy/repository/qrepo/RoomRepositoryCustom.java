package com.roomy.repository.qrepo;

import com.roomy.dto.GuestDTO;

import java.util.List;

public interface RoomRepositoryCustom {

    List<GuestDTO> loadRecent4Guest(Long roomId);
}
