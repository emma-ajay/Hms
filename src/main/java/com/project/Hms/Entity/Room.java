package com.project.Hms.Entity;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )

    private Long roomId;
    private String roomNumber;
    private Boolean isFull;
    private Boolean isReserved;
    private Long floorId;
    private Long wingId;
    private Long hallId;
    private Long memberCount;


    @ManyToOne
    @JoinColumn(name = "hallId", insertable = false, nullable = false,updatable = false)
    private Hall hall;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Report> reportSet;

    public Set<Report> getReportSet() {
        return reportSet;
    }

    public void setReportSet(Set<Report> reportSet) {
        this.reportSet = reportSet;
    }

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> userSet;

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Room() {
    }

    public Room(String roomNumber, Boolean isFull, Boolean isReserved, Long floorId, Long wingId, Long hallId, Long memberCount) {
        this.roomNumber = roomNumber;
        this.isFull = isFull;
        this.isReserved = isReserved;
        this.floorId = floorId;
        this.wingId = wingId;
        this.hallId = hallId;
        this.memberCount = memberCount;
    }

    public Room(Long roomId, String roomNumber, Boolean isFull, Boolean isReserved, Long floorId, Long wingId, Long hallId, Long memberCount) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.isFull = isFull;
        this.isReserved = isReserved;
        this.floorId = floorId;
        this.wingId = wingId;
        this.hallId = hallId;
        this.memberCount = memberCount;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Boolean getFull() {
        return isFull;
    }

    public void setFull(Boolean full) {
        isFull = full;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getWingId() {
        return wingId;
    }

    public void setWingId(Long wingId) {
        this.wingId = wingId;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public Long getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Long memberCount) {
        this.memberCount = memberCount;
    }
}
