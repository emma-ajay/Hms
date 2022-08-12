package com.project.Hms.DTO.Requests;

public class CreateRoom {
    private String roomNumber;

    private Long hallId;

    private Long floorId;

    private Long wingId;

    public CreateRoom() {
    }

    public CreateRoom(String roomNumber, Long hallId, Long floorId, Long wingId) {
        this.roomNumber = roomNumber;
        this.hallId = hallId;
        this.floorId = floorId;
        this.wingId = wingId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
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
}
