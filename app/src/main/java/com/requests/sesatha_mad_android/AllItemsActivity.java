package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.requests.sesatha_mad_android.adapters.allItemsAdapter;
import com.requests.sesatha_mad_android.adapters.myItemsAdapter;
import com.requests.sesatha_mad_android.interfaces.ItemClickListener;
import com.requests.sesatha_mad_android.models.Item;

public class AllItemsActivity extends AppCompatActivity {

    //navigation bar variables
    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;

    private RecyclerView recyView;
    allItemsAdapter adapter;
    Query db;

    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        category = getIntent().getStringExtra("Category");

        //navigation bar
        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Customize action bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));

        //Get firebase instance
        db = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("items")
                .orderByChild("category").equalTo(category);

        //initialize recycler view
        recyView = findViewById(R.id.AllListingsRecycler);
        recyView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Item> data= new FirebaseRecyclerOptions.Builder<Item>().setQuery(db,Item.class).build();

        //initialize adapter
        adapter = new allItemsAdapter(data);

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Item model, int position) {
                //Toast.makeText(MyItemsActivity.this, model.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AllItemsActivity.this, MyItemDetailsActivity.class);
                intent.putExtra("Data", model);
                startActivity(intent);
            }
        });

        //Connect adapter with the Recycler view
        recyView.setAdapter(adapter);
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

    //Drawer
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}