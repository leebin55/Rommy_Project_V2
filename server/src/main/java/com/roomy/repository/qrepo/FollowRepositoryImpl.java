package com.roomy.repository.qrepo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.roomy.dto.user.UserDTO;
import com.roomy.entity.QFollow;
import com.roomy.entity.QUser;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public Set<UserDTO> findFollowingsByUsername(String username) {
        QFollow follow = QFollow.follow;
        QUser user = QUser.user;

       return (Set<UserDTO>) query.select(Projections.fields(UserDTO.class,user.username,user.nickname,user.email,user.profile))
                .from(follow)
                .leftJoin(follow.toUser,user)
                .where(follow.fromUser.username.eq(username))
                .orderBy(follow.followId.desc())
                .fetch();

    }

    @Override
    public Set<UserDTO> findFollowersByUsername(String username) {
        return null;
    }
}
