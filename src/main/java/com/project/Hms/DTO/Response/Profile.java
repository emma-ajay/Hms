package com.project.Hms.DTO.Response;

public class Profile {
    private String userName;

    private String email;

    private String name;

    private String gender;

    private Long roomId;

    public Profile() {
    }

    public Profile(String userName, String email, String name, String gender, Long roomId) {
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.roomId = roomId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
