package com.project.Hms.Entity;

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
    private Boolean isReserved;


    public Wing() {
    }

    public Wing(Long wingId, String wingName, Boolean isReserved) {
        this.wingId = wingId;
        this.wingName = wingName;
        this.isReserved = isReserved;
    }

    public Wing(String wingName, Boolean isReserved) {
        this.wingName = wingName;
        this.isReserved = isReserved;


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

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }
}
