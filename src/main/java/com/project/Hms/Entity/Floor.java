package com.project.Hms.Entity;

import javax.persistence.*;

@Entity
public class Floor {
    @Id
    @SequenceGenerator(
            name = "floor_sequence",
            sequenceName = "floor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "floor_sequence"
    )

    private Long floorId;
    private String floorName;



    public Floor() {
    }

    public Floor(Long floorId, String floorName) {
        this.floorId = floorId;
        this.floorName = floorName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }
}
