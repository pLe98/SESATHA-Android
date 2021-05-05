package com.requests.sesatha_mad_android;

public class Card {

    private String cardHolder, cardNo, cMonth, cYear, ccv;

    public Card() {
    }

    public Card(String cardHolder, String cardNo, String cMonth, String cYear, String ccv) {
        this.cardHolder = cardHolder;
        this.cardNo = cardNo;
        this.cMonth = cMonth;
        this.cYear = cYear;
        this.ccv = ccv;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getcMonth() {
        return cMonth;
    }

    public void setcMonth(String cMonth) {
        this.cMonth = cMonth;
    }

    public String getcYear() {
        return cYear;
    }

    public void setcYear(String cYear) {
        this.cYear = cYear;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }
}
