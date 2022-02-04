package com.roomy.repository.qrepo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.roomy.dto.user.QUserWithRoomDTO;
import com.roomy.dto.user.UserDTO;
import com.roomy.dto.user.UserWithRoomAndFollowDTO;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.entity.QFollow;
import com.roomy.entity.QRoom;
import com.roomy.entity.QUser;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory query;

    //String username, String email, String profile, String nickname,
    // Long roomId, String intro, String roomName, Long total
    @Override
    public Optional<UserWithRoomDTO> userWithRoomByUsername(String username) {
        QRoom room = QRoom.room;
        QUser user = QUser.user;

       return Optional.ofNullable(query.select(new QUserWithRoomDTO(user.username,
                       user.email, user.profile, user.nickname, room.roomId, room.intro, room.roomName, room.total))
               .from(user)
               .join(user.room, room)
               .where(user.username.eq(username))
               .fetchOne());
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

    @Override
    public UserWithRoomAndFollowDTO loadRoomProfileByUsername(String username) {
        QRoom room = QRoom.room;
        QUser user = QUser.user;
        QFollow follow = QFollow.follow;

        UserWithRoomDTO userWithRoom = userWithRoomByUsername(username).orElse(null);
        List<UserDTO> followingList = query.select(Projections.fields(UserDTO.class,user.username,user.nickname))
                .from(user)
                .join(user.followingList, follow)
                .where(user.username.eq(username))
                .orderBy(follow.followId.desc())
                .fetch();

        return UserWithRoomAndFollowDTO.toUserWitRoomAndFollows(userWithRoom,followingList);
    }
}
