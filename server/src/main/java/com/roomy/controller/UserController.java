package com.roomy.controller;

import com.roomy.config.JWT.TokenProvider;
import com.roomy.dto.LoginDTO;
import com.roomy.dto.UserDTO;
import com.roomy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;

    }

    // user 등록 : 회원가입
    @PostMapping("/")
    public ResponseEntity<?> join(@RequestBody UserDTO userDto) {
        log.debug("User join 컨트롤러 실행 {}",userDto.toString());
       String username =userService.joinUser(userDto);
       if(username!= null){
           return ResponseEntity.ok(username);
       }
        return ResponseEntity.badRequest().body("회원가입 실패");
    }

    @GetMapping("/detail")
    public ResponseEntity<?> loadUserDetailByToken(){
        log.info("user login 후 정보 불러오기");

        return null;
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
