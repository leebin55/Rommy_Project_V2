package com.roomy.controller;

import com.roomy.entity.User;
import com.roomy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class AuthControllerTest {

    @Autowired
    private UserRepository userRepo;

    @BeforeEach
    void signup(){
        userRepo.save(User.builder()
                .username("testId").password("111")
                .nickname("test").email("test@gmail.com").build()
                );
    }

    @Test
    public void login_success() throws Exception{

    }

}