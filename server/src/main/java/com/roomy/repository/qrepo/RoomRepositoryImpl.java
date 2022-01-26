package com.roomy.repository.qrepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.roomy.dto.GuestDTO;
import com.roomy.dto.QGuestDTO;
import com.roomy.dto.user.QUserWithRoomDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.entity.QGuest;
import com.roomy.entity.QRoom;
import com.roomy.entity.QUser;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<GuestDTO> loadRecent4Guest(Long roomId) {
        QRoom room = QRoom.room;
        QGuest guest = QGuest.guest;
        return
                query.select(new QGuestDTO(guest.guestSeq, guest.nickname, guest.username,
                guest.createDate, guest.status, guest.content))
                .from(room)
                .leftJoin(guest)
                .on(room.roomId.eq(roomId))
                .limit(4)
                .orderBy(guest.createDate.desc())
                .fetch();
    }
}
