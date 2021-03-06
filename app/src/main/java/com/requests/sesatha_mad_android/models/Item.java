package com.requests.sesatha_mad_android.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;


public class Item implements Serializable {
    private String id;
    private String title;
    private String category;
    private String description;
    private float price;
    private String imUrl;
    private String userId;
    private boolean approved;
    private String last_modified;

    public Item(){}


    public Item(String title, String category, String description, float price, String imUrl, String userId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imUrl = imUrl;
        this.userId = userId;
        this.approved=false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mma");
        this.last_modified = df.format(Calendar.getInstance().getTime());
    }

    public Item(String title, String category, String description, float price, String userId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.userId = userId;
        this.approved = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mma");
        this.last_modified = df.format(Calendar.getInstance().getTime());
    }

    public void updateItem(String title, String category, String description, float price,String id) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.approved = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mma");
        this.last_modified = df.format(Calendar.getInstance().getTime());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImUrl() {
        return imUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mma");
        this.last_modified = df.format(Calendar.getInstance().getTime());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
