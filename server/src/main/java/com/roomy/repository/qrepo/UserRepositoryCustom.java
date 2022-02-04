package com.roomy.repository.qrepo;

import com.roomy.dto.user.UserWithRoomAndFollowDTO;
import com.roomy.dto.user.UserWithRoomDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {

    Optional<UserWithRoomDTO> userWithRoomByUsername(String username);

    List<UserWithRoomDTO> loadTop4Room();

    UserWithRoomAndFollowDTO loadRoomProfileByUsername(String username);

}
