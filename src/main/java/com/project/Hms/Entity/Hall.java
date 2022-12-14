package com.project.Hms.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Hall {
    @Id
    @SequenceGenerator(
            name = "hall_sequence",
            sequenceName = "hall_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hall_sequence"
    )

    private Long hallId;
    private String hallName;
    private String hallGender;
    private Long hallCapacity;
    private Boolean isReserved;

    @OneToMany(mappedBy = "hall")
    private Set<Report> reportSet;

    @OneToMany(mappedBy = "hall")
    private Set<Room> roomSet;

    public Hall() {
    }

    public Hall(Long hallId, String hallName, String hallGender, Boolean isReserved) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.hallGender = hallGender;
        this.isReserved = isReserved;
    }

    public Hall(String hallName, String hallGender, Boolean isReserved) {
        this.hallName = hallName;
        this.hallGender = hallGender;
        this.isReserved = isReserved;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getHallGender() {
        return hallGender;
    }

    public void setHallGender(String hallGender) {
        this.hallGender = hallGender;
    }

    public Long getHallCapacity() {
        return hallCapacity;
    }

    public void setHallCapacity(Long hallCapacity) {
        this.hallCapacity = hallCapacity;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        this.isReserved = reserved;
    }


}
