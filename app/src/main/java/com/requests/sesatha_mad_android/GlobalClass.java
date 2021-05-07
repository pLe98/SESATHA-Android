package com.requests.sesatha_mad_android;

import android.app.Application;

import com.requests.sesatha_mad_android.models.User;

public class GlobalClass extends Application {
    private String username;
    private String userId;
    private User user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
