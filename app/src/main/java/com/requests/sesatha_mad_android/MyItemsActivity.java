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
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.requests.sesatha_mad_android.adapters.myItemsAdapter;
import com.requests.sesatha_mad_android.interfaces.ItemClickListener;
import com.requests.sesatha_mad_android.models.Item;

public class MyItemsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //navigation bar variables
    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;
    NavigationView navView;

    private RecyclerView recyView;
    myItemsAdapter adapter;
    Query db;

    FloatingActionButton addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

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
        navView =(NavigationView)findViewById(R.id.activity_main_nav_view);
        navView.setNavigationItemSelectedListener(this);

        //Get firebase instance
        db = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("items");

        //initialize recycler view
        recyView = findViewById(R.id.MyListingsRecycler);
        recyView.setLayoutManager(new LinearLayoutManager(this));

        //add btn
        addBtn = findViewById(R.id.btn_add);

        //Firebase Query
        FirebaseRecyclerOptions<Item> data= new FirebaseRecyclerOptions.Builder<Item>().setQuery(db,Item.class).build();

        //initialize adapter
        adapter = new myItemsAdapter(data);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyItemsActivity.this, PostItem.class);
                startActivity(intent);
            }
        });

        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Item model, int position) {
                //Toast.makeText(MyItemsActivity.this, model.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyItemsActivity.this, MyItemDetailsActivity.class);
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