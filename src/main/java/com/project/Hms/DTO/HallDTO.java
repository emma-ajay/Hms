package com.project.Hms.DTO;

import javax.validation.constraints.NotNull;

public class HallDTO {


    private String hallName;
    private String hallGender;
    private Long hallCapacity;
    private Boolean isReserved;

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
        isReserved = reserved;
    }


    @Override
    public String toString() {
        return "CreateHallDTO{" +
                "hallName='" + hallName + '\'' +
                ", hallGender='" + hallGender + '\'' +
                ", hallCapacity='" + hallCapacity + '\'' +
                ", isReserved=" + isReserved +
                '}';
    }



}
