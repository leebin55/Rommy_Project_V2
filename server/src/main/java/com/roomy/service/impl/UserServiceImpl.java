package com.roomy.service.impl;

import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.entity.Room;
import com.roomy.entity.User;
import com.roomy.repository.UserRepository;
import com.roomy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Override // 모든 user 불러오기
    public Page<UserDTO> getAllUserList() {

        PageRequest pageReq = PageRequest.of(0,15,
                Sort.by(Sort.Direction.DESC,"username"));
        Page<User> userVOPage = userRepository.findAll(pageReq);

        Page<UserDTO> userPage = userVOPage.map(user -> UserDTO.builder().username(user.getUsername())
                .nickname(user.getNickname()).profile(user.getProfile()).email(user.getEmail())
                .build());

        return userPage;
    }


    @Override //username 으로 찾기
    public UserDTO findByUsername(String username) {
        User findUser = userRepository.findById(username).orElse(null);
        if(findUser==null){
            return null;
        }
        return UserDTO.builder().username(findUser.getUsername())
                .nickname(findUser.getNickname()).profile(findUser.getProfile())
                .email(findUser.getEmail()).build();
    }

    @Override
    public UserWithRoomDTO loadUserAndRoom(String username) {
        UserWithRoomDTO userWithRoom = userRepository.userWithRoomByUsername(username);
        log.info("user detail with room : {}", userWithRoom.toString());
        return userWithRoom;
    }

    @Override // username 중복 검사
    public Boolean validateDuplicateUser(String username) {
        log.info("user existByUsername ");
      Boolean existCheck = userRepository.existsByUsername(username);
      return existCheck;
    }

    // user 가입하면 해당 유저의 room 도 같이 생성
    @Override
    public String joinUser(UserDTO userDTO) {
        // 중복검사를 이미 했지만 user 를 insert 하기전 다시 확인> username이 있는데 save 하면 update 되기 때문에
        Boolean existCheck = validateDuplicateUser(userDTO.getUsername());
        if(existCheck == false){
            // 우선 UserDTO 를 UserVO 로 변환
            UserDTO encodedUser = userDTO.toBuilder().password(passwordEncoder.encode(userDTO.getPassword())).build();
            log.info("join user to builder 사용 : {}",encodedUser.toString());
            User user = encodedUser.toEntity();
            // user 가 생성되면  room 도 같이 생성되게
            Room room = Room.builder().roomName(user.getNickname()+" 님")
                    .intro(makeIntro(user.getNickname()))
                    .build();
            // 양뱡향 이므로 room 에 user 세팅
           room.setUser(user);
            userRepository.save(user);
            return user.getUsername();
        }
        return null;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        log.info("userservice update ");
        User user = userRepository.findById(userDTO.getUsername()).orElse(null);
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
}
