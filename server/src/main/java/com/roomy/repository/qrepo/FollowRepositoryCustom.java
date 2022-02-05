package com.roomy.repository.qrepo;

import com.roomy.dto.user.UserDTO;

import java.util.List;
import java.util.Set;

public interface FollowRepositoryCustom {

    Set<UserDTO> findFollowingsByUsername(String username);

    Set<UserDTO> findFollowersByUsername(String username);
}
