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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hallId",
            nullable = false)
    @JsonIgnore
    private Hall hall;

    public Wing() {
    }

    public Wing(Long wingId, String wingName, Hall hall) {
        this.wingId = wingId;
        this.wingName = wingName;
    }

    public Wing(String wingName, Hall hall) {
        this.wingName = wingName;
        this.hall = hall;

    }

    public String getWingName() {
        return wingName;
    }

    public void setWingName(String wingName) {
        this.wingName = wingName;
    }


    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
