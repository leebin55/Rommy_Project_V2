package com.roomy.service;

import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import org.springframework.data.domain.Page;

public interface UserService{


    public Page<UserDTO> getAllUserWithPage();

    UserDTO findByUsername(String username);

    UserWithRoomDTO getUserAndRoomByUsername(String username);

    // 아이디 중복 검사
    public Boolean validateDuplicateUser(String username);

    public String joinUser(UserDTO userDTO);

    public void updateUser(UserDTO userDTO);

    void deleteUser(String username);

}
