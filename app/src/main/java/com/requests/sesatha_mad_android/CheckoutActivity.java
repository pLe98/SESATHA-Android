package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckoutActivity extends AppCompatActivity {

    //toolbar
    Toolbar mytoolbar;

    //checkout activity
    private Button addressChange, paymentChange, purchase;
    private TextView nametv, add1tv, add2tv, add3tv, phonetv, cardnotv, cardExptv,  subTotaltv, noItemstv, shippingtv, totaltv, totaltv2 ;


    //card details
    GlobalClass globalVariables;
    private String userid, cardno, cmonth, cyear;
    private Float subtotal, shipping, total;
    private int noOfItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("Order Confirmation");

        //xml values
        nametv = findViewById(R.id.ch_name);
        add1tv = findViewById(R.id.ch_address1);
        add2tv= findViewById(R.id.ch_address2);
        add3tv = findViewById(R.id.ch_address3);
        phonetv = findViewById(R.id.ch_phone);

        cardnotv = findViewById(R.id.ch_cardNo);
        cardExptv = findViewById(R.id.ch_exp);
        //cardCCVtv = findViewById(R.id.ch_ccv);

        subTotaltv = findViewById(R.id.ch_subtotal);
        totaltv = findViewById(R.id.ch_total);
        shippingtv = findViewById(R.id.ch_shipping);
        noItemstv = findViewById(R.id.ch_noItems);

        totaltv2 = findViewById(R.id.ch_totalFinal);


        //user details
        globalVariables = (GlobalClass) getApplicationContext();
        userid = globalVariables.getUser().getUserID();

        //button actions
        addressChange = findViewById(R.id.chgAddress);
        paymentChange = findViewById(R.id.chgCard);
        purchase = findViewById(R.id.puchasebtn);

        //display
        showCardDetails();
        //display user data
        showUserDetails();

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

    public void showCardDetails(){
        DatabaseReference db = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        db.child("Card").orderByChild("userID").equalTo(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Log.e("card details", "card found");
                    //retrive values from db
                    String cardNum = snapshot.child(userid).child("cardNo").getValue().toString();
                    cardno = "XXXX XXXX XXXX "+cardNum.substring(cardNum.length()-4);
                    cmonth = snapshot.child(userid).child("cMonth").getValue().toString();
                    cyear = snapshot.child(userid).child("cYear").getValue().toString();
                    //ccv = snapshot.child(userid).child("ccv").getValue().toString();

                    //assign into text views
                    cardnotv.setText(cardno);
                    cardExptv.setText(cmonth + " / " + cyear);
                    //cardCCVtv.setText(ccv);

                }else{
                    Log.e("card details", "no card");
                    String cNo = "XXXX XXXX XXXX XXXX";
                    cardnotv.setText(cNo);
                    cardExptv.setText( "XX / XX");
                    //cardCCVtv.setText("XXX");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void showUserDetails(){
        //getting user data from global variable and display in text view

        nametv.setText(globalVariables.getUser().getUserName());
        add1tv.setText(globalVariables.getUser().getAddress1());
        add2tv.setText(globalVariables.getUser().getAddress2());
        add3tv.setText(globalVariables.getUser().getAddress3());
        phonetv.setText(String.valueOf(globalVariables.getUser().getPhone()));

    }
}