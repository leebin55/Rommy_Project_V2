package com.roomy.dto;

import com.roomy.model.User;
import com.roomy.model.othertype.Gender;
import lombok.*;

@Getter @ToString @Builder

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

    public User toEntity(){
        return User.builder().username(username).gender(gender)
                .email(email).profile(profile).nickname(nickname).password(password).build();

    }

}
