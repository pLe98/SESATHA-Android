package com.requests.sesatha_mad_android.models;

public class Cart {
    private String itemNo, title;
    private int qty;
    private float unitPrice, shipping;

    public Cart() {
    }

    public Cart(String itemNo, String title, int qty, float unitPrice, float shipping) {
        this.itemNo = itemNo;
        this.title = title;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.shipping = shipping;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getShipping() {
        return shipping;
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }
}
