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

    //String username, String email, String profile, String nickname,
    // Long roomId, String intro, String roomName, Long total
    @Override
    public UserWithRoomDTO userWithRoomByUsername(String username) {
        QRoom room = QRoom.room;
        QUser user = QUser.user;

       return query.select(new QUserWithRoomDTO(user.username,
                        user.email, user.profile, user.nickname, room.roomId,room.intro,room.roomName, room.total))
                .from(user)
                .join(user.room,room)
                .where(user.username.eq(username))
                .fetchOne();
    }
    @Override
    public List<UserWithRoomDTO> loadTop4Room(){
        QRoom room = QRoom.room;
        QUser user = QUser.user;

        return query.select(new QUserWithRoomDTO(user.username,
                        user.email, user.profile, user.nickname, room.roomId,room.intro,room.roomName, room.total))
                .from(user)
                .leftJoin(user.room,room)
                .orderBy(room.total.desc())
                .limit(4)
                .fetch();
    }
}
