package com.roomy.service.impl;

import com.roomy.dto.UserDTO;
import com.roomy.model.Room;
import com.roomy.model.User;
import com.roomy.repository.RoomRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service @Transactional
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoomRepository roomRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    /**
                AuthenticationFilter 에서 username 을사용하여 현재 저장되어 있는 user(Account)를
     *      *  UserDetailsService에 요청한다.
     *      *  유저를 받아온 후 password일치 여부등, 유저를 검증하여 성공했다면
     *      *  Authentication 객체를 반환하고 실패했다면 예외를 발생
     *      *  . Provider가 요청한 user를 받아오기 위해 loadByUsername 메서드가 호출됩니다
     */


    @Override
    public Page<UserDTO> getAllUserList() {

        PageRequest pageReq = PageRequest.of(0,15,
                Sort.by(Sort.Direction.DESC,"username"));
        Page<User> userVOPage = userRepository.findAllWithPage(pageReq);

        Page<UserDTO> userPage = userVOPage.map(user -> new UserDTO(user.getUsername(),user.getNickname(),user.getProfile(),user.getEmail()));

        return userPage;
    }


    @Override
    public UserDTO findByUsername(String username) {
        User findUser = userRepository.findByUsername(username);
        if(findUser==null){
            return null;
        }
        return new UserDTO(findUser.getUsername(), findUser.getEmail(), findUser.getProfile(), findUser.getNickname());
    }

    @Override
    public Boolean validateDuplicateUser(String username) {
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
            User user = userDTO.toEntity();
            // user 가 생성되면  room 도 같이 생성되게
            Room room = new Room(user.getNickname() + " 님",makeIntro(user.getNickname()));
            // 양뱡향 이므로 room 에 user 세팅
            room.setUser(user);
            user.toBuilder().room(room).build();
            // room 도 insert
            userRepository.save(user);

            return user.getUsername();
        }
        return null;
    }

    @Override
    public void updateUser(UserDTO userDTO) {

        User userVO = userRepository.findById(userDTO.getUsername()).orElse(null);
        if(userVO != null){
         // update 할수 있는 칼럼은  nickname , email, profile\
            // password  는 따로 수정
            userVO.toBuilder().nickname(userDTO.getNickname()).
                    email(userDTO.getEmail()).profile(userDTO.getProfile())
                            .build();
            // room도 수정해주기
            Room room =userVO.getRoom();
            room.setUser(userVO);

            userRepository.save(userVO);
            roomRepository.save(room);
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
