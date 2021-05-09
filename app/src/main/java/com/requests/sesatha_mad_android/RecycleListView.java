package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.requests.sesatha_mad_android.adapters.OrderViewAdapter;
import com.requests.sesatha_mad_android.models.OrderDetails;

import java.util.ArrayList;

public class RecycleListView extends AppCompatActivity implements OrderViewAdapter.OnNoteListener {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    OrderViewAdapter myAdapter;
    ArrayList<OrderDetails> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_list_view);

        databaseReference = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Orders");

//        databaseReference= FirebaseDatabase.getInstance().getReference("Orders");
        Log.d("RLV", "DB ref = "+databaseReference);
        list = new ArrayList<>();
       recyclerView= findViewById(R.id.Recycle);// Initializ Recle view
        Log.d("RLV", "RV = "+recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        myAdapter = new OrderViewAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    OrderDetails OrderDetails =dataSnapshot.getValue(OrderDetails.class);
                    list.add(OrderDetails);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void OnNoteListener(int posistion) {
        Intent intent =new Intent(this,CartActivity.class);
        startActivity(intent);

    }
}