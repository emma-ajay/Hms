package com.project.Hms.DTO.Requests;

public class CreateReport {
    private String report;

    public CreateReport() {
    }

    public CreateReport(String report) {
        this.report = report;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
