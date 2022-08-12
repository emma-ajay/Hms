package com.project.Hms.DTO;

public class WingDTO {
    private String wingName;

    public String getWingName() {
        return wingName;
    }
    public void setWingName(String wingName) {
        this.wingName = wingName;
    }

    @Override
    public String toString() {
        return "WingDTO{" +
                "wingName='" + wingName + '\'' +
                '}';
    }
}
