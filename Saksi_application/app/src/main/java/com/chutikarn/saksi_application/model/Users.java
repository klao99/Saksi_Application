package com.chutikarn.saksi_application.model;

public class Users {
    private String name;
    private String number;
    private String email;
    private String userType;
    private String profile;

    public Users() {
    }

    public Users(String name, String number, String email, String userType, String profile) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.userType = userType;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
