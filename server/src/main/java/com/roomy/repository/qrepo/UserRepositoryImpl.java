package com.roomy.repository.qrepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.roomy.dto.user.QUserWithRoomDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.entity.QRoom;
import com.roomy.entity.QUser;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public UserWithRoomDTO userWithRoomByUsername(String username) {
        QRoom room = QRoom.room;
        QUser user = QUser.user;

       return query.select(new QUserWithRoomDTO(user.username,
                        user.email, user.profile, user.nickname, room.roomId))
                .from(user)
                .join(user.room,room)
                .where(user.username.eq(username))
                .fetchOne();
    }
    @Override
    public List<UserWithRoomDTO> loadTop4Room(){
        QRoom room = QRoom.room;
        QUser user = QUser.user;

        //String username, String profile, Long roomId, String roomName, Long total
        return query.select(new QUserWithRoomDTO(user.username,user.profile,user.nickname,
                        room.roomId, room.roomName, room.total))
                .from(user)
                .join(room)
                .on(user.username.eq(room.user.username))
                .orderBy(room.total.desc())
                .limit(4)
                .fetch();
    }
}
