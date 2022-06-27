package com.roomy.service.impl;

import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.entity.Room;
import com.roomy.entity.User;
import com.roomy.exception.InvalidException;
import com.roomy.repository.UserRepository;
import com.roomy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service @Transactional
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Page<UserDTO> getAllUserWithPage() {
        PageRequest pageReq = PageRequest.of(0,15,
                Sort.by(Sort.Direction.DESC,"username"));

        Page<User> userVOPage = userRepository.findAll(pageReq);

        Page<UserDTO> userPage = userVOPage.map(user -> UserDTO.builder().username(user.getUsername())
                .nickname(user.getNickname()).profile(user.getProfile()).email(user.getEmail())
                .build());

        return userPage;
    }

    @Override
    public UserDTO findByUsername(String username) {
        User findUser = getUserById(username);
        if(findUser==null){
            return null;
        }
        return UserDTO.builder().username(findUser.getUsername())
                .nickname(findUser.getNickname()).profile(findUser.getProfile())
                .email(findUser.getEmail()).build();
    }

    @Override
    public UserWithRoomDTO getUserAndRoomByUsername(String username) {
        UserWithRoomDTO userWithRoom = userRepository.userWithRoomByUsername(username).orElse(null);
        log.info("user detail with room : {}", userWithRoom.toString());
        return userWithRoom;
    }

    @Override
    public Boolean validateDuplicateUser(String username) {
      return userRepository.existsByUsername(username);
    }

    @Override
    public String joinUser(UserDTO userDTO) {
        Boolean existCheck = validateDuplicateUser(userDTO.getUsername());
        if(existCheck == true){
            new InvalidException("이미 회원가입한 회원입니다.");
        }
        UserDTO encodedUser = userDTO.toBuilder().password(passwordEncoder.encode(userDTO.getPassword())).build();
        User user = encodedUser.toEntity();
        // user 가 생성되면  room 도 같이 생성
        Room room = Room.builder().roomName(user.getNickname()+" 님")
                .intro(makeIntro(user.getNickname()))
                .build();
        room.setUser(user);
        userRepository.save(user);
        return user.getUsername();
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = getUserById(userDTO.getUsername());
        if(user != null){
            user.updateNicknameAndProfile(userDTO.getNickname(),userDTO.getProfile());
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    private String makeIntro(String nickname){
        return "여기는 "+ nickname + "님의 ROOM 입니다.";
    }

    private User getUserById(String username){
       return  userRepository.findById(username).orElse(null);
    }
}



