package com.project.Hms.Repository;
import com.project.Hms.Entity.Floor;
import com.project.Hms.Entity.Report;
import com.project.Hms.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT u FROM Report u WHERE u.userId = ?1")
    List<Report> findReportsByUserId(Long id);

    List<Report> findReportsByRoomId(Long roomId);

    List<Report> findReportsByHallId(Long hallIId);

    @Query("SELECT u FROM Report u WHERE u.hasBeenResolved = true")
    List<Report> findReportsByResolved();

    @Query("SELECT u FROM Report u WHERE u.hasBeenResolved = false")
    List<Report> findReportsByNotResolved();

    @Modifying
    @Query("Update Report u SET u.hasBeenResolved = true, u.modifiedDate =?1   WHERE u.reportId =?1")
    void resolveReport(String modifiedDate, Long reportId);

    @Query("SELECT u FROM Report u WHERE u.reportId = ?1")
    Report findReportsByReportId(Long reportId);



}
