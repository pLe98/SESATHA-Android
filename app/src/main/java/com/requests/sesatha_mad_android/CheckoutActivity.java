package com.requests.sesatha_mad_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class CheckoutActivity extends AppCompatActivity {

    //toolbar
    Toolbar mytoolbar;

    //checkout activity
    private Button addressChange, paymentChange, purchase;
    private TextInputLayout name, add1, add2, add3, phone, cardno, cardMonth, cardYear, cardCCV, subTotal, noItems, shipping, total ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("Order Confirmation");

        //xml values



        //button actions
        addressChange = findViewById(R.id.chgAddress);
        paymentChange = findViewById(R.id.chgCard);
        purchase = findViewById(R.id.puchasebtn);

        //open edit profile activity
        addressChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        //open payment add activity
        paymentChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, AddPaymentActivity.class);
                startActivity(intent);
            }
        });

        //place order
        addressChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }
}