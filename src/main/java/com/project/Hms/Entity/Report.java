package com.project.Hms.Entity;
import javax.persistence.*;

@Entity
public class Report {
    @Id
    @SequenceGenerator(
            name = "report_sequence",
            sequenceName = "report_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "report_sequence"
    )

    private Long reportId;
    private String report;
    private Boolean hasBeenResolved;
    private Long userId;
    private Long roomId;
    private Long hallId;
    private String createdDate;
    private String modifiedDate;





    public Report() {
    }

    public Report(String report, Boolean hasBeenResolved, Long userId, Long roomId, Long hallId, String createdDate, String modifiedDate) {
        this.report = report;
        this.hasBeenResolved = hasBeenResolved;
        this.userId = userId;
        this.roomId = roomId;
        this.hallId = hallId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Report(Long reportId, String report, Boolean hasBeenResolved, Long userId, Long roomId, Long hallId, String createdDate, String modifiedDate) {
        this.reportId = reportId;
        this.report = report;
        this.hasBeenResolved = hasBeenResolved;
        this.userId = userId;
        this.roomId = roomId;
        this.hallId = hallId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getReportId() {
        return reportId;
    }



    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Boolean getHasBeenResolved() {
        return hasBeenResolved;
    }

    public void setHasBeenResolved(Boolean hasBeenResolved) {
        this.hasBeenResolved = hasBeenResolved;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
