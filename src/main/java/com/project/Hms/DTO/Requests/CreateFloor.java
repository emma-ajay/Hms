package com.project.Hms.DTO.Requests;

public class CreateFloor {


    private String floorName;
    private Boolean isReserved;

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String hallName) {
        this.floorName = hallName;
    }


    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }


    @Override
    public String toString() {
        return "CreateHallDTO{" +
                "hallName='" + floorName + '\'' +
                ", isReserved=" + isReserved +
                '}';
    }



}
