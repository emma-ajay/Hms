package com.project.Hms.Repository;
import com.project.Hms.Entity.Floor;
import com.project.Hms.Entity.Wing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WingRepository extends JpaRepository<Wing, Long> {

    @Query("SELECT u FROM Wing u WHERE u.wingName=?1")
    Wing findWingByName(String wingName);

    @Query("SELECT u FROM Wing u WHERE u.wingId=?1")
    Wing getWingById(Long wingId);


}
