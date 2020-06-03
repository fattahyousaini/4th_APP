package com.myapplicationdev.android.rpreportfault;

public class UserInfo {

    private String userId;
    private String description;
    private String location;
    private String email;

    public UserInfo() {
    }

    public UserInfo(String userId, String description, String location, String email){
        this.userId = userId;
        this.description = description;
        this.location = location;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location){
        location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

}
