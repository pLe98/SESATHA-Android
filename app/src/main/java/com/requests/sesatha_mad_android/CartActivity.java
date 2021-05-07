package com.requests.sesatha_mad_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.requests.sesatha_mad_android.adapters.myItemsAdapter;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartItem;
    myItemsAdapter adapter;
    Query data;

    private String userID;
    GlobalClass globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        globalVariables = (GlobalClass) getApplicationContext();
        userID = globalVariables.getUser().getUserID();

        data = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Cart").child(userID).limitToFirst(10);
    }
}