package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.requests.sesatha_mad_android.adapters.OrderViewAdapter;
import com.requests.sesatha_mad_android.models.OrderDetails;

import java.util.ArrayList;

public class RecycleList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    OrderViewAdapter myAdapter;
    ArrayList<OrderDetails> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);

        recyclerView= findViewById(R.id.Recycle);
        databaseReference= FirebaseDatabase.getInstance().getReference("orderDetails");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
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
}