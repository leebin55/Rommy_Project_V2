package com.roomy.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.model.QRoom;
import com.roomy.model.QUser;
import lombok.RequiredArgsConstructor;

import static org.springframework.util.StringUtils.hasText;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public UserWithRoomDTO userWithRoomByUsername(String username) {
        QRoom room = QRoom.room;
        QUser user = QUser.user;

        return query.select(Projections.fields(UserWithRoomDTO.class,
                user.username, room.roomSeq,room,room.intro,room.roomName))
                .from(user)
                .where(usernameEq(username))
                .join(room)
                .fetchOne();
    }
    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? QUser.user.username.eq(username):null;
    }
}
