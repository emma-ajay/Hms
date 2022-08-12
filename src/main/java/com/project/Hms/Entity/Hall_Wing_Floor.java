package com.project.Hms.Entity;

import javax.persistence.*;

@Entity
public class Hall_Wing_Floor {
    @Id
    @SequenceGenerator(
            name = "hall_wing_floor",
            sequenceName = "hall_wing_floor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hall_wing_floor_sequence"
    )

    private Long Id;

    private Long hallId;

    private Long wingId;

    private Long floorId;

    public Hall_Wing_Floor() {
    }

    public Hall_Wing_Floor(Long hallId, Long wingId, Long floorId) {
        this.hallId = hallId;
        this.wingId = wingId;
        this.floorId = floorId;
    }

    public Hall_Wing_Floor(Long hallId, Long wingId) {
        this.hallId = hallId;
        this.wingId = wingId;
    }

    public Hall_Wing_Floor(Long id, Long hallId, Long wingId, Long floorId) {
        Id = id;
        this.hallId = hallId;
        this.wingId = wingId;
        this.floorId = floorId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public Long getWingId() {
        return wingId;
    }

    public void setWingId(Long wingId) {
        this.wingId = wingId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }


}
