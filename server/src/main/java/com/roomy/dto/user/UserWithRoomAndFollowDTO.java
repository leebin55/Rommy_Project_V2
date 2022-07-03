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
    private  boolean isFollow;

    public static UserWithRoomAndFollowDTO createUserWitRoomAndFollows(UserWithRoomDTO userWithRoom,List<UserDTO> followingList){
        return new UserWithRoomAndFollowDTO(userWithRoom,followingList);
    }

    public static UserWithRoomAndFollowDTO setIsFollow(UserWithRoomAndFollowDTO dto, boolean isFollow ){
            dto.setIsFollow(isFollow);
            return dto;
    }

    private UserWithRoomAndFollowDTO(UserWithRoomDTO userWithRoom,List<UserDTO> followingList) {
        this.userWithRoom = userWithRoom;
        this.followingList = followingList;
    }

    private void setIsFollow(boolean isFollow){
        this.isFollow = isFollow;
    }
}
