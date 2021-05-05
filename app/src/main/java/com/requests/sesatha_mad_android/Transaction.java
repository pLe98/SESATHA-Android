package com.requests.sesatha_mad_android;

public class Transaction {

    private String  transactionID, orderID, userID, paymentDate,amount;

    public Transaction() {
    }

    public Transaction(String transactionID, String orderID, String userID, String paymentDate, String amount) {
        this.transactionID = transactionID;
        this.orderID = orderID;
        this.userID = userID;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
