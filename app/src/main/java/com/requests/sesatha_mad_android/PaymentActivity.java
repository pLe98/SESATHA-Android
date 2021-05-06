package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PaymentActivity extends MainActivity {

    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;

    //payment activity
    Button editbt;

    //card details
    GlobalClass globalVariables;
    String userid, name, cardno, cmonth, cyear, ccv;
    TextView tvName, tvNo, tvMonth, tvYear, tvCcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //toolbar
        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("Payment");

        //navigation bar//navigation bar
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //user details from global variable
        globalVariables = (GlobalClass) getApplicationContext();
        userid = globalVariables.getUser().getUserID();

        //assign varibles
        tvNo = findViewById(R.id.card_number);

        showCardNo();

        //getCardDetails();

        //payment activity
        editbt = (Button) findViewById(R.id.card_edit);

        editbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditPayment();

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openEditPayment(){
        Intent intent = new Intent(this, AddPaymentActivity.class);
        startActivity(intent);

    }

    public void getCardDetails(){
        DatabaseReference db = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        db.child("Card").orderByChild("userID").equalTo(userid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Log.e("card details", "user id found");
                    Card crd = task.getResult().getChildren().iterator().next().getValue(Card.class);
                    name = crd.getCardHolder();
                    cardno = crd.getCardNo();
                    cmonth = crd.getcMonth();
                    cyear = crd.getcYear();
                    ccv = crd.getCcv();
                }else{
                    Log.e("card details", "user id no found");
                }


            }
        });

    }

    public void showCardNo(){
        DatabaseReference db = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        db.child("Card").orderByChild("userID").equalTo(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Log.e("card details", "card found");
                    String cardNum = snapshot.child(userid).child("cardNo").getValue().toString();
                    String cNo = "XXXX XXXX XXXX "+cardNum.substring(cardNum.length()-4);
                    tvNo.setText(cNo);
                }else{
                    Log.e("card details", "no card");
                    String cNo = "XXXX XXXX XXXX XXXX";
                    tvNo.setText(cNo);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}