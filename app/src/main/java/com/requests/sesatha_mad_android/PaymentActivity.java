package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.requests.sesatha_mad_android.adapters.transactionAdapter;
import com.requests.sesatha_mad_android.models.Transaction;

public class PaymentActivity extends MainActivity implements NavigationView.OnNavigationItemSelectedListener{

    //transaction recycler view
    private RecyclerView recViewTransaction;
    transactionAdapter adapter;
    Query transDb;

    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;
    NavigationView navView;

    //payment activity
    Button editbt,deletebtn;

    //card details
    GlobalClass globalVariables;
    String userid, name, cardno, cmonth, cyear, ccv;
    TextView tvName, tvNo, tvMonth, tvYear, tvCcv;
    FirebaseDatabase database;

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
        //Customize action bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));
        navView =(NavigationView)findViewById(R.id.activity_main_nav_view);
        navView.setNavigationItemSelectedListener(this);

        //user details from global variable
        globalVariables = (GlobalClass) getApplicationContext();
        userid = globalVariables.getUser().getUserID();

        //assign varibles
        tvNo = findViewById(R.id.card_number);

        transDb = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Transaction").child(userid);
        recViewTransaction = findViewById(R.id.transaction_recycler);
        recViewTransaction.setLayoutManager(new LinearLayoutManager(this));

        //Recycleer view
        FirebaseRecyclerOptions<Transaction> data = new FirebaseRecyclerOptions.Builder<Transaction>().setQuery(transDb, Transaction.class).build();
        adapter = new transactionAdapter(data);
        recViewTransaction.setAdapter(adapter);


        showCardNo();

        //getCardDetails();

        //payment activity
        editbt = (Button) findViewById(R.id.card_edit);
        deletebtn = (Button) findViewById(R.id.card_delete);

        editbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditPayment();
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCard();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
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

    public void deleteCard(){
        database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
        database.getReference("Card").child(userid).removeValue();

        showCardNo();

        Toast.makeText(PaymentActivity.this,
                "Card Details Removed", Toast.LENGTH_SHORT).show();

    }
/*
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

    }*/

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
                Log.e("card details", "card not found");
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.nav_home:
                intent = new Intent(this,CategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_cart:
                intent = new Intent(this,CartActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_myItems:
                intent = new Intent(this,MyItemsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_payment:
                intent = new Intent(this,PaymentActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_orders:
                intent = new Intent(this,MyOrdersActivity.class);
                startActivity(intent);
                break;
        }
        mdrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


}