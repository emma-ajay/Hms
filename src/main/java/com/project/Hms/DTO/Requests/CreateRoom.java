package com.project.Hms.DTO.Requests;

public class CreateRoom {
    private String roomNumber;

    private String hallName;

    private String floorName;

    private String wingName;

    public CreateRoom() {
    }

    public CreateRoom(String roomNumber, String hallName, String floorName, String wingName) {
        this.roomNumber = roomNumber;
        this.hallName = hallName;
        this.floorName = floorName;
        this.wingName = wingName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getWingName() {
        return wingName;
    }

    public void setWingName(String wingName) {
        this.wingName = wingName;
    }
}
