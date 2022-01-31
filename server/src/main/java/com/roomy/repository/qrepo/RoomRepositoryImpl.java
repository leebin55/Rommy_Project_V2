package com.roomy.repository.qrepo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.roomy.dto.*;
import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.entity.QBoard;
import com.roomy.entity.QGuest;
import com.roomy.entity.QRoom;
import com.roomy.entity.othertype.BoardCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<GuestDTO> findRecentGuestByRoomId(Long roomId) {
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
    public RecentBoardAndGuestDTO findRecentAllBoardsByRoomId(Long roomId) {

        QRoom room = QRoom.room;
        QBoard board = QBoard.board;
        List<GuestDTO> recentGuests = findRecentGuestByRoomId(roomId);

        List<BoardDTO> recentBoards = query.select(new QBoardDTO(board.boardSeq, board.title, board.status))
                .from(room)
                .join(room.boardList, board)
                .orderBy(board.createDate.desc())
                .limit(5)
                .where(board.boardCode.eq(BoardCode.General))
                .fetch();

        List<BoardDTO> recentGallerys = query.select(new QBoardDTO((board.boardSeq), board.title, board.status))
                .from(room)
                .join(room.boardList, board)
                .orderBy(board.createDate.desc())
                .limit(5)
                .where(board.boardCode.eq(BoardCode.Gallery))
                .fetch();


        return RecentBoardAndGuestDTO.RecentBoardAndGuest(recentBoards,recentGallerys,recentGuests);
    }

    @Override
    public Slice<GuestDTO> findGuestByRoomIdWithPage(Long roomId, Pageable pageable) {
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
