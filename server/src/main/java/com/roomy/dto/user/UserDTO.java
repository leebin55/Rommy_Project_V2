package com.roomy.dto.user;

import com.roomy.model.UserVO;
import com.roomy.model.othertype.Birth;
import com.roomy.model.othertype.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter @ToString
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
        this.nickname = nickname;
    }

    public UserDTO() {
    }

    public UserVO toEntity(){
        UserVO user = new UserVO();
        user.setUsername(username);
        user.setBirth(birth);
        user.setGender(gender);
        user.setEmail(email);
        user.setProfile(profile);
        user.setNickname(nickname);
       return user;
    }

}
