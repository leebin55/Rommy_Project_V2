package com.roomy.dto;

import com.roomy.entity.Room;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @ToString
public class RoomDTO {

    private Long roomId;
    private String intro;
    private String roomName;

    public static RoomDTO toDTO(Room room){
        return new RoomDTO(room.getRoomId(),room.getRoomName(),room.getIntro());
    }

    private RoomDTO(Long roomId, String roomName, String intro) {
        this.roomId = roomId;
        this.intro = intro;
        this.roomName = roomName;
    }
}
