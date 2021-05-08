package com.requests.sesatha_mad_android.models;

public class Order {
    private String orderID, itemNo, userID, title, orderDate,status;
    private int noOfItems;
    private float netAmount, shipping, Total;

    public Order() {
    }

    public Order(String orderID, String itemNo, String userID, String title, String orderDate, int noOfItems, float netAmount, float shipping, float total, String status) {
        this.orderID = orderID;
        this.itemNo = itemNo;
        this.userID = userID;
        this.title = title;
        this.orderDate = orderDate;
        this.status = status;
        this.noOfItems = noOfItems;
        this.netAmount = netAmount;
        this.shipping = shipping;
        Total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public float getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(float netAmount) {
        this.netAmount = netAmount;
    }

    public float getShipping() {
        return shipping;
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }
}
