package com.requests.sesatha_mad_android;

public class User {

    private int uId;
    private String username;
    private String uEmail;
    private int uPhone;
    private String uAddress;
    private String uPassword;

    public User() {
    }

    public User(String username, String uEmail, int uPhone, String uAddress, String uPassword) {
        this.username = username;
        this.uEmail = uEmail;
        this.uPhone = uPhone;
        this.uAddress = uAddress;
        this.uPassword = uPassword;
    }

    public int getuId() {
        return uId;
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
