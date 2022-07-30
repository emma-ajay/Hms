package com.project.Hms.DTO.Requests;

public class RegisterRequest {
    private String email;

    private  String userName;

    private String password;

    private String name;

    private String gender;

    private Boolean isActive;


    public RegisterRequest(String email, String password, String name, String gender,  String userName,Boolean isActive ) {
        this.email = email;
        this.userName=userName;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.isActive = isActive;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
