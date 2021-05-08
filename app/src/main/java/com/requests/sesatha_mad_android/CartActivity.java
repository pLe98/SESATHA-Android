package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.requests.sesatha_mad_android.adapters.cartItemAdapter;
import com.requests.sesatha_mad_android.models.Cart;


public class CartActivity extends AppCompatActivity {

    private RecyclerView recyViewCart;
    cartItemAdapter adapter;
    Query db;

    //FirebaseDatabase database;


    Float subtotal, shipping, total;
    TextView tvsubTotal, tvShipping, tvTotal;

    private String userID;
    GlobalClass globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Log.e("cart", "121");

        subtotal = Float.valueOf(0);
        shipping = Float.valueOf(0);
        total = Float.valueOf(0);

        globalVariables = (GlobalClass) getApplicationContext();
        userID = globalVariables.getUser().getUserID();

        db = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Cart").child(userID);


        recyViewCart = findViewById(R.id.cart_recycler);
        recyViewCart.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Cart> data= new FirebaseRecyclerOptions.Builder<Cart>().setQuery(db,Cart.class).build();

        adapter = new cartItemAdapter(data);
        recyViewCart.setAdapter(adapter);

        tvsubTotal = findViewById(R.id.cart_subtotal);
        tvTotal = findViewById(R.id.cart_total);
        tvShipping = findViewById(R.id.cart_shipping);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.e("cart", "1");
                for(DataSnapshot ds : snapshot.getChildren()){
                    //Log.e("cart", "loop");
                    subtotal  += ds.child("unitPrice").getValue(Float.class) * ds.child("qty").getValue(Integer.class);
                    shipping  += ds.child("shipping").getValue(Float.class);
                    Log.e("cart", "loop"+ subtotal + shipping);
                }
                total = subtotal + shipping;
                Log.e("cart", "loop"+ total);

                tvsubTotal.setText(String.valueOf(subtotal));
                tvTotal.setText(String.valueOf(total));
                tvShipping.setText(String.valueOf(shipping));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("cart", "error");
            }
        });


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