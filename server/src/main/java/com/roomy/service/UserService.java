package com.roomy.service;

import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserSimpleDTO;
import org.springframework.data.domain.Page;

public interface UserService{


    public Page<UserSimpleDTO> getAllUserList();

    public UserDTO findById(String username);

    // 아이디 중복 검사
    public String validateDuplicateUser(String username);

    public String joinUser(UserDTO userDTO);

    public void updateUser(UserDTO userDTO);

    public void deleteUser(String username);
}
