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



    public Floor() {
    }

    public Floor(Long floorId) {
        this.floorId = floorId;

    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }
}
