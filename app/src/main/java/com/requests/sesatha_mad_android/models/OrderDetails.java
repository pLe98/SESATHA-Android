package com.requests.sesatha_mad_android.models;

public class OrderDetails {

    String productName;
    int quantity;
    int amount;


    private OrderDetails(){}

    private OrderDetails(String productName, int quantity, int amount){
        this.productName=productName;
        this.quantity=quantity;
        this.amount=amount;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() { return quantity;
    }

    public int getAmount() {
        return amount;
    }


}
