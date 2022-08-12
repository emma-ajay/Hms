package com.project.Hms.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Wing {
    @Id
    @SequenceGenerator(
            name = "wing_sequence",
            sequenceName = "wing_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wing_sequence"
    )

    private Long wingId;
    private String wingName;


    public Wing() {
    }

    public Wing(Long wingId, String wingName, Hall hall) {
        this.wingId = wingId;
        this.wingName = wingName;
    }

    public Wing(String wingName) {
        this.wingName = wingName;


    }

    public String getWingName() {
        return wingName;
    }

    public void setWingName(String wingName) {
        this.wingName = wingName;
    }

    public Long getWingId() {
        return wingId;
    }

    public void setWingId(Long wingId) {
        this.wingId = wingId;
    }
}
