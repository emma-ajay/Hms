package com.project.Hms.DTO.Requests;

public class RegisterRequest {
    private String email;

    private  String userName;

    private String password;

    private String name;

    private String phoneNumber;

    private String image;

    public RegisterRequest(String email, String password, String name, String phoneNumber, String image, String userName) {
        this.email = email;
        this.userName=userName;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.image = image;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
