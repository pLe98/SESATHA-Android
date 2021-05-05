package com.requests.sesatha_mad_android;

public class User {

    private String userID;
    private String userType;
    private String username;
    private String email;
    private int phone;
    private String address;
    private String password;

    public User() {
    }

    public User(String userID, String userType, String username, String uEmail, int uPhone, String uAddress, String uPassword) {
        this.userID = userID;
        this.userType = userType;
        this.username = username;
        this.email = uEmail;
        this.phone = uPhone;
        this.address = uAddress;
        this.password = uPassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
