package com.roomy.service;

import com.roomy.model.User;
import com.roomy.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userdetailService 실행 : {}", username);
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.debug("해당 유저를 찾을 수 없습니다.");
            throw new UsernameNotFoundException("User notfound in the DB");
        }else{
            log.debug("DB에서 user를 찾았습니다. {}", username);
        }
        String userRole = user.getRole().toString();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);

        return null;
    }

}
