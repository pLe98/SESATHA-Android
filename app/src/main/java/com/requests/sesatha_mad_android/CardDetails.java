package com.requests.sesatha_mad_android;

public class CardDetails {
    private int cardNo;
    private String cardHolder;
    //private String cardType;
    private int year;
    private int month;
    private int ccv;

    public CardDetails() {

    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    //public void setCardType(String cardType) {
       // this.cardType = cardType;
   // }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    public int getCardNo() {
        return cardNo;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    //public String getCardType() {
        //return cardType;
    //}

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getCcv() {
        return ccv;
    }
}
