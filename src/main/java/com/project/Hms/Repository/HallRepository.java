package com.project.Hms.Repository;
import com.project.Hms.Entity.Floor;
import com.project.Hms.Entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    @Query("SELECT h from Hall h WHERE h.hallName=?1 ")
    Hall findHallByName(String hallName);

    @Query("SELECT h from Hall h WHERE h.hallId=?1 ")
    Hall findHallById(Long hallId);

    @Query("SELECT h from Hall h WHERE h.hallGender=?1 ")
    List<Hall> findHallsByGender(String hallGender);

}
