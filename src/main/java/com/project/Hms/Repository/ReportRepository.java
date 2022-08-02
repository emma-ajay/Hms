package com.project.Hms.Repository;
import com.project.Hms.Entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Floor, Long> {

}
