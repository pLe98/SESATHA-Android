package com.requests.sesatha_mad_android;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {

    private String userID;
    private String userType;
    private String username;
    private String uEmail;
    private int uPhone;
    private String uAddress;
    private String uPassword;

    public User() {
    }

    public User(String userID, String userType, String username, String uEmail, int uPhone, String uAddress, String uPassword) {
        this.userID = userID;
        this.userType = userType;
        this.username = username;
        this.uEmail = uEmail;
        this.uPhone = uPhone;
        this.uAddress = uAddress;
        this.uPassword = uPassword;
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

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public int getuPhone() {
        return uPhone;
    }

    public void setuPhone(int uPhone) {
        this.uPhone = uPhone;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }
}
