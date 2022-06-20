package com.roomy.controller;

import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.exception.UserNotFoundException;
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

    @PostMapping("/sign_up")
    public ResponseEntity<?> join(@RequestBody UserDTO userDto) {
       String username =userService.joinUser(userDto);
       if(username != null){
           return ResponseEntity.ok(username);
       }
        return ResponseEntity.badRequest().body("회원가입 실패");
    }

    @GetMapping("/detail")
    public ResponseEntity<?> loadUserDetailByToken(Principal principal){
        if(principal != null){
//            UserDTO loggedUser = userService.findByUsername(principal.getName());
            UserWithRoomDTO userWithRoom = userService.getUserAndRoomByUsername(principal.getName());
            return ResponseEntity.ok(userWithRoom);
        }
        return ResponseEntity.badRequest().body("로그인 정보 없음");
    }

    @GetMapping({"/",""})
    public ResponseEntity<?> list() {
        Page<UserDTO> userList = userService.getAllUserWithPage();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> detail(@PathVariable String username) {
        UserDTO user = userService.findByUsername(username);
        if(user == null){
            new UserNotFoundException("해당 회원이 존재하지 않습니다.");
        }
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{username}")
    public ResponseEntity<?> update(@PathVariable String username, @RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
        return null;
    }
}
