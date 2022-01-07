package com.roomy.service;

import com.roomy.dto.UserDTO;
import org.springframework.data.domain.Page;

public interface UserService{


    public Page<UserDTO> getAllUserList();



    UserDTO findByUsername(String username);

    // 아이디 중복 검사
    public Boolean validateDuplicateUser(String username);

    public String joinUser(UserDTO userDTO);

    public void updateUser(UserDTO userDTO);


    void deleteUser(String username);
}
