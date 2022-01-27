package com.roomy.dto.user;

import com.roomy.entity.User;
import com.roomy.entity.othertype.UserRole;
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
                .email(email).profile(profile).nickname(nickname)
                .role(UserRole.ROLE_USER).password(password).build();
    }


}
