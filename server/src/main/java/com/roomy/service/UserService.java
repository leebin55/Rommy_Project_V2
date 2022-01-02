package com.roomy.service;

import com.roomy.dto.UserDTO;

import java.util.List;

public interface UserService{


    List<UserDTO> getAllUserList();

    UserDTO findById(String username);

    void createUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUser(String username);
}
