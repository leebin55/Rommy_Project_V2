package com.roomy.service;

import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserSimpleDTO;
import org.springframework.data.domain.Page;

public interface UserService{


    public Page<UserSimpleDTO> getAllUserList();

    UserDTO findById(Long userId);

    UserSimpleDTO findByUsername(String username);

    // 아이디 중복 검사
    public Boolean validateDuplicateUser(String username);

    public String joinUser(UserDTO userDTO);

    public void updateUser(UserDTO userDTO);


    void deleteUser(Long userId);
}
