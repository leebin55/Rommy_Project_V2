package com.roomy.service.impl;

import com.roomy.dto.UserDTO;
import com.roomy.model.UserVO;
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

import static java.util.stream.Collectors.toList;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO user = userRepository.findById(username).get();
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


    @Override
    public List<UserDTO> getAllUserList() {
        List<UserVO> userVOList = userRepository.findAll();

        List<UserDTO> userList = userVOList.stream().map(user -> toUserDTO(user))
                .collect(toList());

        return userList;
    }

    @Override
    public UserDTO findById(String username) {

        UserVO userVO = userRepository.findById(username).orElse(null);
        if(userVO != null){
            return toUserDTO(userVO);
        }
        return null;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        UserVO userVO = userDTO.toEntity();
        userRepository.save(userVO);
    }

    @Override
    public void updateUser(UserDTO userDTO) {

        UserVO userVO = userRepository.findById(userDTO.getUsername()).orElse(null);
        if(userVO != null){
         // update 할수 있는 칼럼은  nickname , email , password, profile
            userVO.setNickname(userDTO.getNickname());
            userVO.setEmail(userDTO.getEmail());
            userVO.setProfile(userDTO.getProfile());
            userVO.setPassword(userDTO.getPassword());
        }
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    private UserDTO toUserDTO(UserVO VO){
        return new UserDTO(VO.getUsername(),
                VO.getEmail(), VO.getBirth(), VO.getProfile(),VO.getGender(),VO.getNickname());
    }
}
