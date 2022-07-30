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
    private Boolean hasBeenResolved;




    public Report() {
    }






}
