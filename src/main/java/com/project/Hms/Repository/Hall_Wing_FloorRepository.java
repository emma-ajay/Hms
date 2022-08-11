package com.project.Hms.Repository;

import com.project.Hms.Entity.Hall_Wing_Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Hall_Wing_FloorRepository extends JpaRepository<Hall_Wing_Floor,Long> {

    @Query("SELECT u FROM Hall_Wing_Floor u WHERE u.hallId =?1 AND u.wingId =?1")
    List<Hall_Wing_Floor> getAssignedHallWingById(Long hallId, Long wingId);

    @Query("SELECT DISTINCT wingId FROM Hall_Wing_Floor u WHERE u.hallId =?1 ")
    List<Long> viewAllWingsInHall(Long hallId);

    @Query("SELECT floorId FROM Hall_Wing_Floor u  WHERE u.hallId =?1 AND u.wingId =?1 ")
    List<Long> viewAllFloorsInWingHAll(Long wingId, Long hallId);

    @Query("SELECT u FROM Hall_Wing_Floor u WHERE u.hallId =?1 AND u.wingId =?1 AND u.floorId =?1")
   List<Hall_Wing_Floor> getAssignedHallWingFloorById(Long hallId, Long wingId, Long floorId);
}
