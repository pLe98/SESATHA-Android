package com.requests.sesatha_mad_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.requests.sesatha_mad_android.adapters.cartItemAdapter;
import com.requests.sesatha_mad_android.models.Cart;


public class CartActivity extends AppCompatActivity {

    private RecyclerView recyViewCart;
    cartItemAdapter adapter;
    Query db;

    private String userID;
    GlobalClass globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        globalVariables = (GlobalClass) getApplicationContext();
        userID = globalVariables.getUser().getUserID();

        db = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Cart").child(userID);

        recyViewCart = findViewById(R.id.cart_recycler);
        recyViewCart.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Cart> data= new FirebaseRecyclerOptions.Builder<Cart>().setQuery(db,Cart.class).build();

        adapter = new cartItemAdapter(data);
        recyViewCart.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening(); //adapter keeps updating the recycler view when realtime-db updates
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening(); //Stops getting new data when activity exits
    }
}