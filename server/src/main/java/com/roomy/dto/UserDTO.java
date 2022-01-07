package com.roomy.dto;

import com.roomy.model.UserVO;
import com.roomy.model.othertype.Gender;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class UserDTO {

    private String username;
    private String email;
    private String profile;
    private Gender gender;
    private String password;
    private String nickname;


    // (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
    protected UserDTO() {
    }

    // 회원 모든정보(회원가입)
    public UserDTO(String username, String email, String profile, Gender gender,String nickname,String password) {

        this.username = username;
        this.email = email;
        this.profile = profile;
        this.gender = gender;
        this.nickname = nickname;
        this.password = password;
    }

    // 로그인 할때
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 기본정보
    public UserDTO(String username, String nickname, String email, String profile) {
        this.username = username;
        this.email= email;
        this.profile = profile;
        this.nickname = nickname;
    }

    public UserVO toEntity(){
        UserVO user = new UserVO();
        user.setUsername(username);
        user.setGender(gender);
        user.setEmail(email);
        user.setProfile(profile);
        user.setNickname(nickname);
        user.setPassword(password);
       return user;
    }

}
