package com.roomy.controller;

import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserSimpleDTO;
import com.roomy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // user 등록 : 회원가입
    @PostMapping({"/",""})
    public ResponseEntity<?> join(@RequestBody UserDTO userDTO) {
        log.debug("User join 컨트롤러 실행 {}",userDTO.toString());
       String username =userService.joinUser(userDTO);
       if(username!= null){
           return ResponseEntity.ok(username);
       }
        return ResponseEntity.badRequest().body("회원가입 실패");
    }

    // user 조회
    @GetMapping({"/",""})
    public ResponseEntity<?> list() {
        log.debug("user list 컨트롤러 실행 ");
        Page<UserSimpleDTO> userList = userService.getAllUserList();
        return ResponseEntity.ok(userList);
    }



    // user 상세정보
    @GetMapping("/{username}")
    public ResponseEntity<?> detail(@PathVariable String username) {
        log.debug("user detail 컨트롤러 실행 {}",username);
        UserSimpleDTO user = userService.findByUsername(username);
        if(user == null){
            return ResponseEntity.badRequest().body("해당 회원이 존재하지 않습니다.");
        }
        return ResponseEntity.ok(user);
    }
}