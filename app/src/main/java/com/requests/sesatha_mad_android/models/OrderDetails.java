package com.requests.sesatha_mad_android.models;


public class OrderDetails {



    String itemNo;
    double netAmount;
    int noOfItems;
    String orderDate;
    String orderID;
    double shipping;
    String status;
    String title;
    double total;
    String userID;



    private OrderDetails(){}

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    //    String tilte;
//    int quantity;
//    int amount;

//
//    private OrderDetails(String productName, int quantity, int amount){
//        this.productName=productName;
//        this.quantity=quantity;
//        this.amount=amount;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public int getQuantity() { return quantity;
//    }
//
//    public int getAmount() {
//        return amount;
//    }



}


