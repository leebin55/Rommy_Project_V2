package com.roomy.service.impl;

import com.roomy.entity.User;
import com.roomy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userdetailService 실행 : {}", username);
        User user = userRepository.findById(username).orElse(null);
        if(user == null){
            log.debug("해당 유저를 찾을 수 없습니다.");
            throw new UsernameNotFoundException("User notfound in the DB");
        }else{
            log.debug("DB에서 user를 찾았습니다. {}", username);
            String userRole = user.getRole().toString();
            log.info("userdetail service 해당 유저 찾음 role: {}" , userRole);
            // 우선 회원가입을 하면 ROLE_USER 으로만 세팅을 해줄거기 때문에
            List<SimpleGrantedAuthority> authority = Collections.singletonList(new SimpleGrantedAuthority(userRole));

            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),authority);
        }


    }

}
