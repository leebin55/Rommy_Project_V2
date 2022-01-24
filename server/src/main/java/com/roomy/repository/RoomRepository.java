package com.roomy.repository;

import com.roomy.entity.Room;
import com.roomy.repository.qrepo.RoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long>, RoomRepositoryCustom {


}
