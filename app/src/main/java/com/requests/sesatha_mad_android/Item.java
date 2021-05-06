package com.requests.sesatha_mad_android;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Item {
    public String title;
    public String category;
    public String description;
    public float price;
    public String imUrl;
    public String userId;
    public boolean approved;
    public String last_modified;

    public Item(){}

    public Item(String title, String category, String description, float price, String imUrl, String userId) {
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
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.userId = userId;
        this.approved = approved;
        this.last_modified = last_modified;
    }

    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
    }
}
