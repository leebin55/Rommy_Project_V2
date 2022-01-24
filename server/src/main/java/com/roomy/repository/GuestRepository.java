package com.roomy.repository;

import com.roomy.entity.Guest;
import com.roomy.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest,Long> {

    // 미니홈피 메인에서 보여줄 방명록 리스트
    // 미니홈피 주인회원 id로 select 해오고 gusetSeq로 내림차순 정렬해서 top 4개만 반환
    List<Guest> findTop4ByRoomOrderByCreateDateDesc(Room room);
}
