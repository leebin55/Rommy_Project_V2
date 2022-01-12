package com.roomy.dto;

import com.roomy.model.User;
import lombok.*;
// (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString @Builder(toBuilder = true)
public class UserDTO {

    private String username;
    private String email;
    private String profile;
    private String password;
    private String nickname;



    public User toEntity(){
        return User.builder().username(username)
                .email(email).profile(profile).nickname(nickname).password(password).build();

    }

}
