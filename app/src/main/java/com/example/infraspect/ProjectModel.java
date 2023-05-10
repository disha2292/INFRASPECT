package com.example.infraspect;

public class ProjectModel {

    private String projectName,address,startDate,endDate;



    public ProjectModel() {


    }

    public ProjectModel(String projectName, String address, String startDate, String endDate) {
        this.projectName = projectName;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

//    public void setEdittext(String task) {
//    }
}

