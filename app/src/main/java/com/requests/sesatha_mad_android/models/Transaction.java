package com.requests.sesatha_mad_android.models;

public class Transaction {

    private String transactionID, orderDate;
    private float amount;

    public Transaction() {
    }

    public Transaction(String transactionID, String orderDate, float amount) {
        this.transactionID = transactionID;
        this.orderDate = orderDate;
        this.amount = amount;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
