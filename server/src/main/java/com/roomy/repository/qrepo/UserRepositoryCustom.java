package com.roomy.repository.qrepo;

import com.roomy.dto.user.UserWithRoomDTO;

public interface UserRepositoryCustom {

    UserWithRoomDTO userWithRoomByUsername(String username);
}
