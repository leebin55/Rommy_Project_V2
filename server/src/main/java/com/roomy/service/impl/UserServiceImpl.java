package com.roomy.service.impl;

import com.roomy.model.UserVO;
import com.roomy.model.othertype.UserRole;
import com.roomy.repository.UserRepository;
import com.roomy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserVO user = userRepository.findById(userId).get();
        if(user == null){
            log.debug("해당 유저를 찾을 수 없습니다.");
            throw new UsernameNotFoundException("User notfound in the DB");
        }else{
            log.debug("DB에서 user를 찾았습니다. {}", userId);
        }
        UserRole userRole = user.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);

        return null;
    }
    @Override
    public List<UserVO> selectAll() {
        return null;
    }

    @Override
    public UserVO findById(String s) {
        return null;
    }

    @Override
    public void insert(UserVO userVO) {

    }

    @Override
    public void update(UserVO userVO) {

    }

    @Override
    public void delete(String s) {

    }


}
