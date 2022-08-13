package com.project.Hms.Repository;

import com.project.Hms.Entity.Hall_Wing_Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Hall_Wing_FloorRepository extends JpaRepository<Hall_Wing_Floor,Long> {

    @Query("SELECT u FROM Hall_Wing_Floor u WHERE u.hallId =:hallId AND u.wingId =:wingId")
    List<Hall_Wing_Floor> getAssignedHallWingById(@Param("hallId") Long hallId, @Param("wingId") Long wingId);


    @Query("SELECT DISTINCT wingId FROM Hall_Wing_Floor u WHERE u.hallId =?1 ")
    List<Long> viewAllWingsInHall(Long hallId);

    @Query("SELECT DISTINCT floorId FROM Hall_Wing_Floor u  WHERE u.hallId =:hallId AND u.wingId =:wingId ")
    List<Long> viewAllFloorsInWingHAll(@Param("hallId") Long hallId, @Param("wingId") Long wingId);

    @Query("SELECT u FROM Hall_Wing_Floor u WHERE u.hallId =:hallId AND u.wingId =:wingId AND u.floorId =:floorId")
   List<Hall_Wing_Floor> getAssignedHallWingFloorById(@Param("hallId") Long hallId, @Param("wingId") Long wingId, @Param("floorId") Long floorId);
}
