package com.roomy.service;

import com.roomy.model.UserVO;

import java.util.List;

public interface UserService{


    List<UserVO> selectAll();

    UserVO findById(String s);

    void insert(UserVO userVO);

    void update(UserVO userVO);

    void delete(String s);
}
