package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class CategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //navigation bar variables
    protected DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;
    NavigationView navView;


    TextView userGreet;
    ImageButton all,art,jewel,home,crafts,shoes;
    GlobalClass globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //navigation bar
        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navView =(NavigationView)findViewById(R.id.activity_main_nav_view);
        navView.setNavigationItemSelectedListener(this);


        //Customize action bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));

        //Retrieve Global Application Object
        globalVariables = (GlobalClass) getApplicationContext();

        //Set greeting
        userGreet = findViewById(R.id.textViewHeadingMyItems);
        userGreet.setText("Hi "+ globalVariables.getUser().getUserName()+'!');
        userGreet.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_default));

        all = findViewById(R.id.cat_all);
        art = findViewById(R.id.cat_art);
        jewel = findViewById(R.id.cat_jewellery);
        home =findViewById(R.id.cat_home);
        crafts = findViewById(R.id.cat_craft);
        shoes = findViewById(R.id.cat_clothing);

        //Set onClick callbacks
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicks(0);
            }
        });

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicks(1);
            }
        });

        jewel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicks(2);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicks(3);
            }
        });

        crafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicks(4);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClicks(5);
            }
        });



    }

    //callback method for button clicks
    private void handleClicks(int index){
        String catText = getCatText(index);
        Intent intent = new Intent(CategoryActivity.this, AllItemsActivity.class);
        intent.putExtra("Category",catText);
        startActivity(intent);
    }

    private String getCatText(int index){
        switch(index) {
            case 1:
               return  "Art & collectibles";

            case  2:
                return "Jewellery & accessories";

            case 3:
                return "Home & living";

            case 4:
                return "Crafts & tools";

            case 5:
                return "Clothing & shoes";

            default:
                return "";
        }

    }

    //navigation bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("Menu",String.valueOf(item.getItemId()));
        Intent intent;
        switch(item.getItemId()){
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