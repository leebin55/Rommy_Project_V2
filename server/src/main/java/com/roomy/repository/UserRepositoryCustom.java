package com.roomy.repository;

import com.roomy.dto.user.UserWithRoomDTO;

public interface UserRepositoryCustom {

    UserWithRoomDTO userWithRoomByUsername(String username);
}
