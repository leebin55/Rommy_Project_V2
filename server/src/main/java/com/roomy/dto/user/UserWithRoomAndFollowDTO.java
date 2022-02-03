package com.roomy.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString
public class UserWithRoomAndFollowDTO {

    private UserWithRoomDTO userWithRoom;
    private List<UserDTO> followingList;

    public static UserWithRoomAndFollowDTO toUserWitRoomAndFollows(UserWithRoomDTO userWithRoom,List<UserDTO> followingList){
        return new UserWithRoomAndFollowDTO(userWithRoom,followingList);
    }

    private UserWithRoomAndFollowDTO(UserWithRoomDTO userWithRoom,List<UserDTO> followingList) {
        this.userWithRoom = userWithRoom;
        this.followingList = followingList;
    }
}
