package com.roomy.repository.qrepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.roomy.dto.GuestDTO;
import com.roomy.dto.QGuestDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.entity.QGuest;
import com.roomy.entity.QRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

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
                .join(room.guestList,guest)
                .limit(4)
                .orderBy(guest.createDate.desc())
                .fetch();
    }

    @Override
    public Slice<GuestDTO> loadGuestsByRoomId(Long roomId, Pageable pageable) {
        QRoom room = QRoom.room;
        QGuest guest = QGuest.guest;
        List<GuestDTO> content = query.select(new QGuestDTO(guest.guestSeq, guest.nickname, guest.username,
                        guest.createDate, guest.status, guest.content))
                .from(room)
                .leftJoin(room.guestList, guest)
                .orderBy(guest.createDate.desc())
                .where(room.roomId.eq(roomId))
                .offset(pageable.getOffset()) //page * size 반환
                .limit(pageable.getPageSize())
                .fetch();

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(content,pageable,hasNext);
    }

    @Override
    public UserWithRoomDTO loadUserAndRoom(Long roomId) {

//        QRoom room = QRoom.room;
//        QUser user = QUser.user;
////        String username, String profile, String nickname, Long roomId
//        query.select(new QUserWithRoomDTO(user.username, user.profile,user.nickname,room.roomId))
//                .from(room);

        return null;
    }
}
