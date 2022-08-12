package com.project.Hms.Repository;
import com.project.Hms.Entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FloorRepository extends JpaRepository<Floor, Long> {

    @Query("SELECT u FROM Floor u WHERE u.floorName=?1")
    Floor findFloorByName(String floorName);

    @Query("SELECT u FROM Floor u WHERE u.floorId=?1")
    Floor findFloorById(Long floorId);
}
