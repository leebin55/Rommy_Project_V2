package com.roomy.repository.qrepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.roomy.dto.user.QUserWithBoardDTO;
import com.roomy.dto.user.UserWithBoardDTO;
import com.roomy.entity.QBoard;
import com.roomy.entity.QUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public UserWithBoardDTO loadBoardDetail(Long boardSeq) {
        QUser user = QUser.user;
        QBoard board =QBoard.board;
        //String profile, String nickname, String username, String title,
        // String content, String createDate, Long boardHit, Long likeCount
        return query.select(new QUserWithBoardDTO(user.profile, user.nickname,
                user.username, board.title,board.content,board.createDate, board.boardHit
                ,board.likeCount))
                .from(board)
                .join(user)
                .on(board.boardSeq.eq(boardSeq))
                .fetchOne();

    }
}
