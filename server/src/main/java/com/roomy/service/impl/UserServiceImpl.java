package com.roomy.service.impl;

import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserSimpleDTO;
import com.roomy.model.RoomVO;
import com.roomy.model.UserVO;
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

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoomRepository roomRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
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
    public Page<UserSimpleDTO> getAllUserList() {

        PageRequest pageReq = PageRequest.of(0,15,
                Sort.by(Sort.Direction.DESC,"username"));
        Page<UserVO> userVOPage = userRepository.findAllWithPage(pageReq);

        Page<UserSimpleDTO> userPage = userVOPage.map(user -> new UserSimpleDTO(user.getUsername(),user.getEmail(),user.getNickname()));

        return userPage;
    }

    @Override
    public UserDTO findById(String username) {

        Optional<UserVO> userVO = userRepository.findById(username);
        if(userVO != null){
            return toUserDTO(userVO.get());
        }
        return null;
    }

    @Override
    public String validateDuplicateUser(String username) {
        UserDTO findUser = findById(username);
        if(findUser != null){
            return findUser.getUsername();
        }
        return null;
    }

    // user 가입하면 해당 유저의 room 도 같이 생성
    @Override
    public String joinUser(UserDTO userDTO) {
        // 중복검사를 이미 했지만 user 를 insert 하기전 다시 확인> username이 있는데 save 하면 update 되기 때문에
        String findUser = validateDuplicateUser(userDTO.getUsername());

        if(findUser == null){
            // 우선 UserDTO 를 UserVO 로 변환
            UserVO user = userDTO.toEntity();
            // user 가 생성되면  room 도 같이 생성되게
            RoomVO room = new RoomVO(user.getNickname() + " 님",makeIntro(user.getNickname()));
            // 양뱡향 이므로 room 에 user 세팅
            room.setUser(user);
            user.setRoom(room);
            // room 도 insert
            userRepository.save(user);

            return user.getUsername();
        }
        return null;
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

            // room도 수정해주기
            RoomVO room =userVO.getRoom();
            room.setUser(userVO);

            userRepository.save(userVO);
            roomRepository.save(room);
        }
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }



    // UserVO 클래스에 메소드를 만드는게 나을지 고민..
    private UserDTO toUserDTO(UserVO VO){
        return new UserDTO(VO.getUsername(),
                VO.getEmail(), VO.getBirth(), VO.getProfile(),VO.getGender(),VO.getNickname());
    }

    private String makeIntro(String nickname){
        return "여기는 "+ nickname + "님의 ROOM 입니다.";
    }
}
