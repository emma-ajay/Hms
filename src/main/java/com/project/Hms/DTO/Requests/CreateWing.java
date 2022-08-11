package com.project.Hms.DTO.Requests;

public class CreateWing {
    private String wingName;
    private Boolean isReserved;

    public String getWingName() {
        return wingName;
    }
    public void setWingName(String wingName) {
        this.wingName = wingName;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return "WingDTO{" +
                "wingName='" + wingName + '\'' +
                ", isReserved=" + isReserved +
                '}';
    }
}
