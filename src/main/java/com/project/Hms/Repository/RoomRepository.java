package com.project.Hms.Repository;
import com.project.Hms.Entity.Floor;
import com.project.Hms.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT u FROM Room u WHERE u.hallId =?1")
    List<Room> findRoomByHallId(Long hallId);

    Boolean existsByRoomNumber(String roomNumber);

    Room findRoomByRoomNumber(String roomNumber);

    Room findRoomByRoomId(Long roomId);

    @Modifying
    @Query("UPDATE Room u SET u.memberCount=?1 WHERE u.roomId=?1")
    void updateMemberCount(Long newCount,Long roomId);

    @Query("select u from Room u where u.hallId =:hallId and u.wingId =:wingId and u.floorId =:floorId")
    List<Room> findRoomsByHallWingAndFloorId(@Param("hallId")Long hallId, @Param("wingId") Long wingId, @Param("floorId") Long floorId);
}
