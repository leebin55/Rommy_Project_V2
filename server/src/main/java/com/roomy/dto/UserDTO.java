package com.roomy.dto;

import com.roomy.model.UserVO;
import com.roomy.model.othertype.Birth;
import com.roomy.model.othertype.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserDTO {

    private String username;
    private String email;
    private Birth birth;
    private String profile;
    private Gender gender;
    private String password;
    private String nickname;


    public UserDTO(String username, String email, Birth birth, String profile, Gender gender,String nickname) {
        this.username = username;
        this.email = email;
        this.birth = birth;
        this.profile = profile;
        this.gender = gender;
    }

    public UserVO toEntity(){
        return UserVO.builder()
                .username(username)
                .birth(birth)
                .gender(gender)
                .password(password)
                .email(email)
                .profile(profile)
                .nickname(nickname)
                .build();
    }

}
