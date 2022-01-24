package com.roomy.controller;

import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // user 등록 : 회원가입
    @PostMapping("/sign_up")
    public ResponseEntity<?> join(@RequestBody UserDTO userDto) {
        log.debug("User join 컨트롤러 실행 {}",userDto.toString());
       String username =userService.joinUser(userDto);
       if(username!= null){
           return ResponseEntity.ok(username);
       }
        return ResponseEntity.badRequest().body("회원가입 실패");
    }

    // user 정보 + roomId
    @GetMapping("/detail")
    public ResponseEntity<?> loadUserDetailByToken(Principal principal){
        log.info("user login 후 정보 불러오기 : {}", principal.toString());
        if(principal != null){
//            UserDTO loggedUser = userService.findByUsername(principal.getName());
            UserWithRoomDTO userWithRoom = userService.loadUserAndRoom(principal.getName());
            return ResponseEntity.ok(userWithRoom);
        }
        return ResponseEntity.badRequest().body("로그인 정보 없음");
    }

    // 모든 user 조회
    @GetMapping({"/",""})
    public ResponseEntity<?> list() {
        log.debug("user list 컨트롤러 실행 ");
        Page<UserDTO> userList = userService.getAllUserList();
        return ResponseEntity.ok(userList);
    }



    // user 상세정보
    @GetMapping("/{username}")
    public ResponseEntity<?> detail(@PathVariable String username) {
        log.debug("user detail 컨트롤러 실행 {}",username);
        UserDTO user = userService.findByUsername(username);
        if(user == null){
            return ResponseEntity.badRequest().body("해당 회원이 존재하지 않습니다.");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkLogin(){
        // 여기서 토큰이 유효한지 확인..
        return ResponseEntity.ok("ok");
    }
}
